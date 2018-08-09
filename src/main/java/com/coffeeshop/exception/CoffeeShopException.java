package com.coffeeshop.exception;

/**
 * Custom Exception for Coffee Shop
 * @author Chandan Vishwakarma
 */
public abstract class CoffeeShopException extends Exception{

    public CoffeeShopException(String message) {
        super(message);
    }

    public CoffeeShopException(String message, Throwable cause) {
        super(message, cause);
    }

}

