package com.coffeeshop.authentication.form;

import com.coffeeshop.authentication.service.CustomUserDetailsService;
import com.coffeeshop.domain.user.User;
import com.coffeeshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Username/Password based custom authentication configuration
 * @author Chandan Vishwakarma
 */
@Component
public class FormAuthenticationProvider implements AuthenticationProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(FormAuthenticationProvider.class);

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String username = (String) authentication.getPrincipal();
    String password = (String) authentication.getCredentials();

    LOGGER.info("Username {} Password {}",username,password);

    UserDetails user = customUserDetailsService.loadUserByUsername(username);

    LOGGER.info("User Data {}",user);

    if(user==null){
      throw new AuthenticationCredentialsNotFoundException("Credentials don't match");
    }

    User authenticatedUser = userService.findByEmail(user.getUsername());

    //check for password
    if (authenticatedUser != null){
      if (!(new BCryptPasswordEncoder().matches(password, authenticatedUser.getPassword()))){
        throw new AuthenticationCredentialsNotFoundException("Incorrect Password");
      }
    }

    return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
