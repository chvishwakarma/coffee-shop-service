package com.coffeeshop.domain.order.item;

import com.coffeeshop.domain.BaseDomain;
import com.coffeeshop.domain.order.Order;
import com.coffeeshop.domain.product.Product;

import javax.persistence.*;

/**
 * Order items related information
 * @author Chandan Vishwakarma
 */
@Entity
@Table(name = "order_item")
public class OrderItem extends BaseDomain{

    /**
     * order item belong to which product
     */
    @OneToOne
    Product product;

    /**
     * many order items can be in single order
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * ordered quantity of particular product
     */
    @Column(name = "quantity",length = 5)
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
