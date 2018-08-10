package com.coffeeshop.domain.order;

import com.coffeeshop.constant.PaymentStatusType;
import com.coffeeshop.constant.PaymentType;
import com.coffeeshop.domain.order.item.OrderItemRequestDTO;
import com.coffeeshop.domain.user.customer.CustomerRequestDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chandan Vishwakarma
 */
public class OrderRequestDTO {

    private CustomerRequestDTO customer;
    private List<OrderItemRequestDTO> items = new ArrayList<>();
    private PaymentType paymentMode;
    private PaymentStatusType paymentStatus;
    private Float discountAmount;

    public CustomerRequestDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRequestDTO customer) {
        this.customer = customer;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }

    public PaymentType getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentType paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentStatusType getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusType paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }
}
