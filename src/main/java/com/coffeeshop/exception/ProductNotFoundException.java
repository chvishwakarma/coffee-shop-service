package com.coffeeshop.exception;

/**
 * @author Chandan Vishwakarma
 */
public class ProductNotFoundException extends CoffeeShopException{

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
