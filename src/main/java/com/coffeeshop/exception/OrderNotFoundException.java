package com.coffeeshop.exception;

/**
 * @author Chandan Vishwakarma
 */
public class OrderNotFoundException extends CoffeeShopException{

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
