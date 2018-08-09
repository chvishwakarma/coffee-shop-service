package com.coffeeshop.repository;

import com.coffeeshop.domain.user.User;
import com.coffeeshop.domain.user.staff.Staff;

/**
 * Staff repository for database related queries
 * @author Chandan Vishwakarma
 */
public interface StaffRepository extends BaseRepository<Staff,Long>{
    Staff findOneByEmail(String email);
}
