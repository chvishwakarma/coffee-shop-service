package com.coffeeshop.util;

import com.coffeeshop.authentication.dto.AuthenticatedUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Common static utility methods available to entire application
 * @author Chandan
 */
@Component
public class CoffeeShopUtils {

    public static AuthenticatedUser getLoggedInUser(){
        try {
            return (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
        }catch (Exception e){
            return null;
        }
    }
}
