package com.coffeeshop.domain.order.item;

import com.coffeeshop.domain.product.ProductItemDTO;

/**
 * @author Chandan Vishwakarma
 */
public class OrderItemWebDTO {

    private ProductItemDTO product;
    private Integer quantity;

    public ProductItemDTO getProduct() {
        return product;
    }

    public void setProduct(ProductItemDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
