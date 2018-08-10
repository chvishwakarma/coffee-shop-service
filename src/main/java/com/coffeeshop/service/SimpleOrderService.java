package com.coffeeshop.service;

import com.coffeeshop.constant.OrderStatusType;
import com.coffeeshop.domain.order.Order;
import com.coffeeshop.domain.order.OrderAssembler;
import com.coffeeshop.domain.order.OrderRequestDTO;
import com.coffeeshop.domain.order.item.OrderItem;
import com.coffeeshop.domain.order.item.OrderItemRequestDTO;
import com.coffeeshop.domain.product.Product;
import com.coffeeshop.domain.user.customer.Customer;
import com.coffeeshop.domain.user.customer.CustomerAssember;
import com.coffeeshop.repository.CustomerRepository;
import com.coffeeshop.repository.OrderRepository;
import com.coffeeshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chandan Vishwakarma
 */
@Service
@Primary
public class SimpleOrderService implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Order create(OrderRequestDTO orderRequestDTO) {

        Float discountAmount = orderRequestDTO.getDiscountAmount();
        Float subTotalAmount = 0F;
        Float totalAmount;

        Order order = OrderAssembler.fromOrderRequestDTO(orderRequestDTO);
        Customer customer = CustomerAssember.fromRequestDTO(orderRequestDTO.getCustomer());
        if(null != customer.getId()){ //if customer already exist
            customer = customerRepository
                    .findOnById(orderRequestDTO
                            .getCustomer().getId());
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequestDTO orderItemRequestDTO : orderRequestDTO.getItems()){ //getting all items and quantity
            Product product = productRepository.findOne(orderItemRequestDTO.getId());
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequestDTO.getQuantity());
            orderItems.add(orderItem);
            subTotalAmount = subTotalAmount + product.getPrice();
        }

        totalAmount = subTotalAmount - discountAmount;

        order.setCustomer(customer);
        order.setOrderItems(orderItems);
        order.setDiscountAmount(discountAmount);
        order.setSubTotalAmount(subTotalAmount);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatusType.RECEIVED);

        Order orderCreated = orderRepository.save(order);

        return orderCreated;
    }

    @Override
    public Order getByID(Long id) {
        return orderRepository.findOnById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
