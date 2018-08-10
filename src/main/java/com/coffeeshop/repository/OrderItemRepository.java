package com.coffeeshop.repository;

import com.coffeeshop.domain.order.Order;
import com.coffeeshop.domain.order.item.OrderItem;
import com.coffeeshop.domain.user.customer.Customer;

import java.util.List;

/**
 * OrderItem repository for database related queries
 * @author Chandan Vishwakarma
 */
public interface OrderItemRepository extends BaseRepository<OrderItem,Long>{
    List<OrderItem> findAllByOrder(Order order);
}
