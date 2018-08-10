package com.coffeeshop.domain.product;

import com.coffeeshop.constant.StatusType;

/**
 * @author Chandan Vishwakarma
 */
public class ProductItemDTO {

    private Long id;
    private String name;
    private String description;
    private Float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
