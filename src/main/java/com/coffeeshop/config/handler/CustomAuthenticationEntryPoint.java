package com.coffeeshop.config.handler;

import com.coffeeshop.domain.Response;
import com.coffeeshop.service.common.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Autowired
  MessageService messageService;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {

    ObjectMapper mapper = new ObjectMapper();
    Response clientResponse = new Response(messageService);

    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");

    clientResponse.setMessage("invalid.jwt")
            .setStatus(com.coffeeshop.constant.ResponseStatus.FAIL)
            .setData("invalid.jwt.detail",true);

    response.getWriter().write(mapper.writeValueAsString(clientResponse));
  }

}
