package com.coffeeshop.authentication.dto;

import com.coffeeshop.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Custom user DTO to configure spring security mechanism
 * @author Chandan Vishwakarma
 */
public class AuthenticatedUser implements UserDetails{

    private User user;

    public AuthenticatedUser(User user) {
        this.user = user;
    }

    /**
     * Added custom SECURITY for securing APIs
     * Based on user type
     * @return collection of GrantedAuthority
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        switch (user.getType()){
            case ADMIN :
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case STAFF:
                authorities.add(new SimpleGrantedAuthority("ROLE_STAFF"));
                break;
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return getUser().getPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(getUser().getEmail());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuthenticatedUser{" +
                "user=" + user.toString() +
                '}';
    }
}
