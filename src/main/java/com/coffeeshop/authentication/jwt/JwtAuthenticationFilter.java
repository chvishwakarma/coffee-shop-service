package com.coffeeshop.authentication.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom JWT filter to return based validity of
 * JWT token received from client
 * @author Chandan Vishwakarma
 * @see com.coffeeshop.config.WebSecurityConfig
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  private String jwtSecretKey;

  public JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, String jwtSecretKey) {
    super(requiresAuthenticationRequestMatcher);
    this.jwtSecretKey = jwtSecretKey;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    String authToken = request.getHeader(JwtUtil.AUTHORIZATION);
    String jwtToken = null;
    if (authToken != null) {
      jwtToken = authToken.replace("Bearer ", "");
    }
    if (JwtUtil.validateToken(jwtSecretKey, buildTokenTO(request, jwtToken))) {
      LOGGER.info("JWT USER TOKEN DETAILS : {}",new JwtAuthToken(jwtToken));
      return getAuthenticationManager().authenticate(new JwtAuthToken(jwtToken));
    }
    throw new AuthenticationCredentialsNotFoundException("JWT Token Not valid");
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException {
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    context.setAuthentication(authResult);
    SecurityContextHolder.setContext(context);
    chain.doFilter(request, response);
  }

  private TokenTO buildTokenTO(HttpServletRequest request, String jwtToken) {
    TokenTO tokenTO = new TokenTO();
    tokenTO.setJwtToken(jwtToken);
    tokenTO.setClientIpAddress(request.getRemoteAddr());
    tokenTO.setBrowserFingerprintDigest(request.getHeader(JwtUtil.USER_AGENT));
    tokenTO.setIssuer(JwtUtil.AUTH_SERVICE_JWT);
    return tokenTO;
  }
}
