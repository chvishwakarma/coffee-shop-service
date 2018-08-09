package com.coffeeshop.service;

import com.coffeeshop.domain.user.User;
import com.coffeeshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Concrete Implementation of UserService
 * @author Chandan Vishwakarma
 * @see UserService
 * @see com.coffeeshop.controller
 * @see com.coffeeshop.authentication
 */
@Primary
@Service
public class SimpleUserService implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User findByID(Long id) {
        return userRepository.findOne(id);
    }
}
