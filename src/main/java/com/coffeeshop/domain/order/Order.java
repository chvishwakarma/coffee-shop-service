package com.coffeeshop.domain.order;

import com.coffeeshop.constant.OrderStatusType;
import com.coffeeshop.constant.PaymentStatusType;
import com.coffeeshop.constant.PaymentType;
import com.coffeeshop.domain.BaseDomain;
import com.coffeeshop.domain.order.item.OrderItem;
import com.coffeeshop.domain.user.customer.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Order related information
 * @author Chandan Vishwakarma
 */
@Entity
@Table(name = "customer_order")
public class Order extends BaseDomain{

    /**
     * many order can belong to single customer
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    /**
     * One order can have multiple products
     */
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * absolute discount amount given to customer on subtotal amount
     */
    @Column(name = "discount_amount")
    private float discountAmount;

    /**
     * sub total amount
     */
    @Column(name = "sub_total_amount")
    private float subTotalAmount;

    /**
     * total amount after discount if any
     */
    @Column(name = "total_amount")
    private float totalAmount;

    /**
     * status of particular order
     * @see com.coffeeshop.constant.OrderStatusType
     */
    @Column(name = "order_status",length = 2)
    private int status;

    /**
     * payment mode used by customer
     * @see com.coffeeshop.constant.PaymentType
     */
    @Column(name = "payment_mode",length = 2)
    private int paymentMode;

    /**
     * payment status of order
     * @see com.coffeeshop.constant.PaymentStatusType
     */
    @Column(name = "payment_status",length = 2)
    private int paymentStatus;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public float getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(float subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatusType getStatus() {
        return OrderStatusType.parse(this.status);
    }

    public void setStatus(OrderStatusType status) {
        this.status = status.getValue();
    }

    public PaymentType getPaymentMode() {
        return PaymentType.parse(this.paymentMode);
    }

    public void setPaymentMode(PaymentType paymentMode) {
        this.paymentMode = paymentMode.getValue();
    }

    public PaymentStatusType getPaymentStatus() {
        return PaymentStatusType.parse(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatusType paymentStatus) {
        this.paymentStatus = paymentStatus.getValue();
    }
}
