package com.coffeeshop.domain.user.customer;

import com.coffeeshop.domain.order.Order;
import com.coffeeshop.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer related information stored in this
 * @author chandan vishwakarma
 */
@Entity
@Table(name = "customer")
public class Customer extends User{

    /**
     * customer full name
     */
    @Column(name = "name", length = 100)
    private String name;

    /**
     * mobile number upto 10 digit
     */
    @Column(name = "mobile", length = 10)
    private String mobile;

    /**
     * staff gender from predefined constant
     * @see com.coffeeshop.constant.GenderType
     */
    @Column(name = "location")
    private String location;

    /**
     * List of all customers order
     */
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orders = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
