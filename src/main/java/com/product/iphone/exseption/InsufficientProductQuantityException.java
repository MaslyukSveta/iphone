package com.product.iphone.exseption;

public class InsufficientProductQuantityException extends RuntimeException {
    public InsufficientProductQuantityException(String productName) {
        super("Insufficient quantity for product: " + productName);
    }
}