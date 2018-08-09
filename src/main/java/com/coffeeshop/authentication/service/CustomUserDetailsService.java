package com.coffeeshop.authentication.service;

import com.coffeeshop.authentication.dto.AuthenticatedUser;
import com.coffeeshop.domain.user.User;
import com.coffeeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetails Service used by spring security configurations
 * @author Chandan Vishwakarma
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public AuthenticatedUser loadUserByUsername(String email) throws UsernameNotFoundException {
    try {
      User user = userService.findByEmail(email);
      if (user == null) {
        throw new UsernameNotFoundException(String.format("User %s doesn't exist!", email));
      }
      return new AuthenticatedUser(user);
    }catch (UsernameNotFoundException e){
      return null;
    }
  }

}
