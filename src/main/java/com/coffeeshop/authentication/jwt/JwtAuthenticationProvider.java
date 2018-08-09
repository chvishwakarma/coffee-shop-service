package com.coffeeshop.authentication.jwt;

import com.coffeeshop.authentication.dto.AuthenticatedUser;
import com.coffeeshop.authentication.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * JWT token based custom authentication provider
 *@author Chandan Vishwakarma
 * @see com.coffeeshop.config.WebSecurityConfig
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

  @Value("${jwt.secret.key}")
  private String jwtSecretKey;

  @Autowired
  CustomUserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String jwtToken = (String) authentication.getCredentials();
    AuthenticatedUser user = JwtUtil.getUser(jwtToken, jwtSecretKey);
    LOGGER.info("JWT Token {} :",jwtToken);
    LOGGER.info("JWT User {} :",user);
    LOGGER.info("JWT Token User {} :",new JwtAuthToken(user.getUsername(), jwtToken, user.getAuthorities()));
    AuthenticatedUser authenticatedUser = userDetailsService.loadUserByUsername(user.getUsername());
    return new JwtAuthToken(authenticatedUser, jwtToken, user.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (JwtAuthToken.class.isAssignableFrom(authentication));
  }
}
