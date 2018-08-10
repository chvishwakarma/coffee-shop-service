package com.coffeeshop.service;

import com.coffeeshop.domain.order.Order;
import com.coffeeshop.domain.order.OrderRequestDTO;

import java.util.List;

public interface OrderService {

    Order create(OrderRequestDTO orderRequestDTO);
    Order getByID(Long id);
    List<Order> getAll();
}
