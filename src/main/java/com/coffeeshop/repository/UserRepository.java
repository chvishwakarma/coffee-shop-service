package com.coffeeshop.repository;

import com.coffeeshop.domain.user.User;

/**
 * User repository for database related queries
 * @author Chandan Vishwakarma
 */
public interface UserRepository extends BaseRepository<User,Long>{
    User findOneByEmail(String email);
}
