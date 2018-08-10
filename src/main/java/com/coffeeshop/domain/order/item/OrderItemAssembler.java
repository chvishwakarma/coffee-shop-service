package com.coffeeshop.domain.order.item;

import com.coffeeshop.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chandan Vishwakarma
 */
public class OrderItemAssembler {

    public static OrderItem fromRequestDTO(OrderItemRequestDTO orderItemRequestDTO){
        final OrderItem orderItem = new OrderItem();
        Product product = new Product();
        product.setId(orderItemRequestDTO.getId());
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemRequestDTO.getQuantity());
        return orderItem;
    }

    public static List<OrderItemRequestDTO> fromOrderItemList(List<OrderItem> orderItemList){
        List<OrderItemRequestDTO> orderItemRequestDTOList = new ArrayList<>();
        for(OrderItem orderItem : orderItemList){
            OrderItemRequestDTO orderItemRequestDTO = new OrderItemRequestDTO();
            orderItemRequestDTO.setId(orderItem.product.getId());
            orderItemRequestDTO.setQuantity(orderItem.getQuantity());
            orderItemRequestDTOList.add(orderItemRequestDTO);
        }
        return orderItemRequestDTOList;
    }
}
