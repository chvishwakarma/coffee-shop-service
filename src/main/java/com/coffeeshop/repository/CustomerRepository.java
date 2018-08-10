package com.coffeeshop.repository;

import com.coffeeshop.domain.product.Product;
import com.coffeeshop.domain.user.customer.Customer;

import java.util.List;

/**
 * Customer repository for database related queries
 * @author Chandan Vishwakarma
 */
public interface CustomerRepository extends BaseRepository<Customer,Long>{
    Customer findOnById(Long id);
    Customer findByMobile(String mobile);
    Customer findByEmail(String email);
}
