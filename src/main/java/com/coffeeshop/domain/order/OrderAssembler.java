package com.coffeeshop.domain.order;

import com.coffeeshop.domain.order.item.OrderItemAssembler;
import com.coffeeshop.domain.user.customer.CustomerAssember;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chandan Vishwakarma
 */
public class OrderAssembler {

    public static Order fromOrderRequestDTO(OrderRequestDTO orderRequestDTO){
        final Order order = new Order();
        order.setPaymentMode(orderRequestDTO.getPaymentMode());
        order.setPaymentStatus(orderRequestDTO.getPaymentStatus());
        return order;
    }

    public static OrderWebDTO fromOrder(Order order){
        final OrderWebDTO orderWebDTO = new OrderWebDTO();
        orderWebDTO.setId(order.getId());
        orderWebDTO.setCustomer(CustomerAssember.fromCustomer(order.getCustomer()));
        orderWebDTO.setItems(OrderItemAssembler.fromOrderItemList(order.getOrderItems()));
        orderWebDTO.setPaymentMode(order.getPaymentMode());
        orderWebDTO.setPaymentStatus(order.getPaymentStatus());
        orderWebDTO.setSubTotalAmount(order.getSubTotalAmount());
        orderWebDTO.setDiscountAmount(order.getDiscountAmount());
        orderWebDTO.setTotalAmount(order.getTotalAmount());
        return orderWebDTO;
    }

    public static List<OrderWebDTO> fromOrderList(List<Order> orderList){
        List<OrderWebDTO> orderWebDTOS = new ArrayList<>();
        for(Order order : orderList){
            OrderWebDTO orderWebDTO = new OrderWebDTO();
            orderWebDTO.setId(order.getId());
            orderWebDTO.setCustomer(CustomerAssember.fromCustomer(order.getCustomer()));
            orderWebDTO.setItems(OrderItemAssembler.fromOrderItemList(order.getOrderItems()));
            orderWebDTO.setPaymentMode(order.getPaymentMode());
            orderWebDTO.setPaymentStatus(order.getPaymentStatus());
            orderWebDTO.setSubTotalAmount(order.getSubTotalAmount());
            orderWebDTO.setDiscountAmount(order.getDiscountAmount());
            orderWebDTO.setTotalAmount(order.getTotalAmount());
            orderWebDTOS.add(orderWebDTO);
        }
        return orderWebDTOS;
    }
}
