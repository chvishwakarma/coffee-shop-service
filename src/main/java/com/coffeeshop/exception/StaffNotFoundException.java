package com.coffeeshop.exception;

/**
 * @author Chandan Vishwakarma
 */
public class StaffNotFoundException extends CoffeeShopException{

    public StaffNotFoundException(String message) {
        super(message);
    }

    public StaffNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
