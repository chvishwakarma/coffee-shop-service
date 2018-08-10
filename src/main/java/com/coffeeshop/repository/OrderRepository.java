package com.coffeeshop.repository;

import com.coffeeshop.domain.order.Order;
import com.coffeeshop.domain.user.customer.Customer;

import java.util.List;

/**
 * Order repository for database related queries
 * @author Chandan Vishwakarma
 */
public interface OrderRepository extends BaseRepository<Order,Long>{
    Order findOnById(Long id);
    List<Order> findAllByCustomer(Customer customer);
}
