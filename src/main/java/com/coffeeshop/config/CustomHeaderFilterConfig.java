package com.coffeeshop.config;

import com.coffeeshop.constant.APIVersion;
import com.coffeeshop.constant.ResponseStatus;
import com.coffeeshop.domain.Response;
import com.coffeeshop.service.common.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom HTTP header validation filter configuration used for
 * REST API VERSIONING
 * @author Chandan Vishwakarma
 * @see com.coffeeshop.config.WebSecurityConfig
 */
@Component
public class CustomHeaderFilterConfig extends OncePerRequestFilter {


    @Autowired
    MessageService messageService;

    private ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException,IllegalStateException {

        mapper = new ObjectMapper();
        Response response1 = new Response(messageService);

        String header =  request.getHeader(APIVersion.CUSTOM_HEADER_KEY);
        System.out.println("header -----------"+header);

        if (header != null && header !=""){

           if (Integer.valueOf(header) == 1){
               filterChain.doFilter(request, response);
            }else {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response1
                        .setStatus(ResponseStatus.FAIL)
                        .setMessage("Invalid API Version")
                        .setData("",false);
               response.getWriter().write(this.mapper.writeValueAsString(response1));
            }
        } else {
           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response1
                    .setStatus(ResponseStatus.FAIL)
                    .setMessage("API VERSION Header is missing")
                    .setData("",false);
           response.getWriter().write(this.mapper.writeValueAsString(response1));
        }
    }

    /**
     * Added swagger paths to enable it access from browser
     * @param request
     * @return true or false for given path
     * @throws ServletException
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        AntPathMatcher pathMatcher = new AntPathMatcher();
        List<String> excludeUrlPatterns = new ArrayList<>();
        excludeUrlPatterns.add("/swagger-ui.html");
        excludeUrlPatterns.add("/v2/api-docs");
        excludeUrlPatterns.add("/configuration/ui");
        excludeUrlPatterns.add("/swagger-resources/configuration/ui");
        excludeUrlPatterns.add("/swagger-resources");
        excludeUrlPatterns.add("/swagger-resources/**");
        excludeUrlPatterns.add("/configuration/security");
        excludeUrlPatterns.add("//swagger-resources/configuration/security");
        excludeUrlPatterns.add("/webjars/**");

        return excludeUrlPatterns.stream()
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }
}
