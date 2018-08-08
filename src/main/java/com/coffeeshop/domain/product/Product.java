package com.coffeeshop.domain.product;

import com.coffeeshop.constant.StatusType;
import com.coffeeshop.domain.BaseDomain;
import com.coffeeshop.domain.order.Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Product related information
 * @author Chandan Vishwakarma
 */
@Entity
@Table(name = "product")
public class Product extends BaseDomain{

    /**
     * name of product
     */
    @Column(name = "name",length = 100)
    private String name;

    /**
     * description of product
     */
    @Column(name = "description",length = 2000)
    private String description;

    /**
     * price of product in floating point
     */
    @Column(name = "price")
    private float price;

    /**
     * total quantity of particular product
     */
    @Column(name = "total_quantity",length = 5)
    private int totalQuantity;

    /**
     * available quantity of particular product
     */
    @Column(name = "available_quantity", length = 5)
    private int availableQuantity;

    /**
     * status of product from predefined constant
     * @see com.coffeeshop.constant.StatusType
     */
    @Column(name = "product_status",length = 2)
    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public StatusType getStatus() {
        return StatusType.parse(this.status);
    }

    public void setStatus(StatusType status) {
        this.status = status.getValue();
    }
}
