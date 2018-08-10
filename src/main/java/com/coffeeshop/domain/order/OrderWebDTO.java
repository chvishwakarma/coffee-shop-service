package com.coffeeshop.domain.order;

import com.coffeeshop.constant.PaymentStatusType;
import com.coffeeshop.constant.PaymentType;
import com.coffeeshop.domain.order.item.OrderItemRequestDTO;
import com.coffeeshop.domain.user.customer.CustomerWebDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chandan Vishwakarma
 */
public class OrderWebDTO {

    private Long id;
    private CustomerWebDTO customer;
    private List<OrderItemRequestDTO> items = new ArrayList<>();
    private PaymentType paymentMode;
    private PaymentStatusType paymentStatus;
    private Float subTotalAmount;
    private Float discountAmount;
    private Float totalAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerWebDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerWebDTO customer) {
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

    public Float getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(Float subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public Float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
