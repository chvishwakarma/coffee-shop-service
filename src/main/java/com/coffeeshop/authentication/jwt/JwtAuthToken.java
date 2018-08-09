package com.coffeeshop.authentication.jwt;

import com.coffeeshop.authentication.dto.AuthenticatedUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Custom configuration of spring security token with JWT
 * @author Chandan Vishwakarma
 */
public class JwtAuthToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = 7916550423807043171L;

  private String token;

  private String username;

  private AuthenticatedUser authenticatedUser=null;

  public JwtAuthToken(String token) {
    super(null);
    this.token = token;
    this.setAuthenticated(false);
  }

  public JwtAuthToken(String username, String token, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.eraseCredentials();
    this.token = token;
    this.username = username;
    this.setAuthenticated(true);
  }

  public JwtAuthToken(AuthenticatedUser authenticatedUser, String token, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.eraseCredentials();
    this.token = token;
    this.username = authenticatedUser.getUsername();
    this.setAuthenticated(true);
    this.authenticatedUser = authenticatedUser;
  }

  @Override
  public Object getDetails() {
    return authenticatedUser;
  }

  @Override
  public Object getCredentials() {
    return token;
  }

  @Override
  public Object getPrincipal() {
    return username;
  }

  @Override
  public void eraseCredentials() {
    super.eraseCredentials();
    this.token = null;
  }
}
