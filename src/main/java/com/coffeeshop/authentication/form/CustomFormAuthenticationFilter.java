package com.coffeeshop.authentication.form;


import com.coffeeshop.authentication.dto.AuthenticatedUser;
import com.coffeeshop.authentication.jwt.JwtUtil;
import com.coffeeshop.authentication.jwt.TokenTO;
import com.coffeeshop.authentication.service.CustomUserDetailsService;
import com.coffeeshop.domain.Response;
import com.coffeeshop.domain.user.User;
import com.coffeeshop.service.common.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Username/Password based login filter and return message based on
 * credentials
 * @author Chandan Vishwakarma
 * @see com.coffeeshop.config.WebSecurityConfig
 */
public class CustomFormAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomFormAuthenticationFilter.class);

  private static final String CHARACTER_ENCODING_UTF_8 = "UTF-8";

  private String jwtSecretKey;

  private ObjectMapper mapper;

  private User user;

  private CustomUserDetailsService userDetailsService;

  private Response response;

  public CustomFormAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, CustomUserDetailsService userDetailsService,
                                        String secretKey, MessageService messageService) {
    super(requiresAuthenticationRequestMatcher);
    this.userDetailsService = userDetailsService;
    this.jwtSecretKey = secretKey;
    this.mapper = new ObjectMapper();
    this.response = new Response(messageService);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    user = mapper.readValue(request.getInputStream(), User.class);

    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
    return this.getAuthenticationManager().authenticate(token);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                          Authentication authentication) throws IOException, ServletException {
    response.setStatus(HttpStatus.OK.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

    String username = (String) authentication.getPrincipal();
    String jwtToken = JwtUtil.getToken(jwtSecretKey, buildTokenTO(userDetailsService.loadUserByUsername(username), request));

    this.response.setMessage("login.successful")
            .setStatus(com.coffeeshop.constant.ResponseStatus.SUCCESS)
            .setData(jwtToken);

    response.getWriter().write(mapper.writeValueAsString(this.response));
    response.setHeader(JwtUtil.HEADER_APP_SUBJECT, authentication.getName());
  }

  private TokenTO buildTokenTO(AuthenticatedUser user, HttpServletRequest request) {
    LocalDateTime ldt = LocalDateTime.now().plusMinutes(480);
    Date expirationDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    Map<String, Object> headerClaims = new HashMap<>();
    headerClaims.put("type", "JWT");
    TokenTO tokenTO = new TokenTO();
    tokenTO.setSubjectId(user.getUsername());
    tokenTO.setSubject(String.valueOf(user.getUser().getId()));
    tokenTO.setClientIpAddress(request.getRemoteAddr());
    tokenTO.setBrowserFingerprintDigest(request.getHeader(JwtUtil.USER_AGENT));
    tokenTO.setIssueDate(new Date());
    tokenTO.setIssuer(JwtUtil.AUTH_SERVICE_JWT);
    tokenTO.setExpirationDate(expirationDate);
    tokenTO.setRoles(getUserRoles(user));
    tokenTO.setHeaderClaims(headerClaims);
    
    return tokenTO;
  }

  private String getUserRoles(AuthenticatedUser user) {
    return String.join(",", user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toList()));
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
      throws IOException, ServletException {

    AuthenticatedUser exist = userDetailsService.loadUserByUsername(user.getEmail());

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(CHARACTER_ENCODING_UTF_8);

    //User Not Exists
    if(null == exist) {
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      this.response.setMessage("invalid.credentials")
              .setStatus(com.coffeeshop.constant.ResponseStatus.FAIL)
              .setData("invalid.credentials",true);
    }

    //Invalid Password
    if((null != exist)
            && (!(new BCryptPasswordEncoder().matches(user.getPassword(), exist.getUser().getPassword())))){
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      this.response.setMessage("invalid.credentials")
              .setStatus(com.coffeeshop.constant.ResponseStatus.FAIL)
              .setData("invalid.credentials",true);
    }

    response.getWriter().write(mapper.writeValueAsString(this.response));

  }

}
