package com.coffeeshop.domain.order;

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
        orderWebDTO.setPaymentMode(order.getPaymentMode());
        return orderWebDTO;
    }
}
