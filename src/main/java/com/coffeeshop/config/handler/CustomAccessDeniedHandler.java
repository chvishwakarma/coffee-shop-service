package com.coffeeshop.config.handler;

import com.coffeeshop.domain.Response;
import com.coffeeshop.service.common.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom access denied message handler configuration
 * @author Chandan Vishwakarma
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Autowired
  MessageService messageService;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
      throws IOException, ServletException {

    ObjectMapper mapper = new ObjectMapper();
    Response clientResponse = new Response(messageService);

    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");

    clientResponse.setMessage("access.denied")
            .setStatus(com.coffeeshop.constant.ResponseStatus.FAIL)
            .setData("access.denied.detail",true);

    response.getWriter().write(mapper.writeValueAsString(clientResponse));
  }

}
