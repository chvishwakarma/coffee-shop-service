package com.coffeeshop.service;

import com.coffeeshop.domain.user.User;

/**
 * User service for the business logic
 * @author Chandan Vishwakarma
 */
public interface UserService {
    /**
     * Find user by email address associated with account
     * @param email
     * @return User object
     */
    User findByEmail(String email);

    /**
     * Find user by primary key id
     * @param id
     * @return User object
     */
    User findByID(Long id);
}
