package com.coffeeshop.exception;

/**
 * Created by superuser on 10-08-2018.
 */
public class StaffNotFoundException extends CoffeeShopException{

    public StaffNotFoundException(String message) {
        super(message);
    }

    public StaffNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
