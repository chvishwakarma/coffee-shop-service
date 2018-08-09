package com.coffeeshop.config.handler;

import com.coffeeshop.domain.Response;
import com.coffeeshop.service.common.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessfulHandler implements LogoutSuccessHandler {

  @Autowired
  MessageService messageService;

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {

    ObjectMapper mapper = new ObjectMapper();
    Response clientResponse = new Response(messageService);

    response.setStatus(HttpStatus.OK.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");

    clientResponse.setMessage("logout.success")
            .setStatus(com.coffeeshop.constant.ResponseStatus.FAIL)
            .setData("logout.success.details",true);

    response.getWriter().write(mapper.writeValueAsString(clientResponse));
  }

}
