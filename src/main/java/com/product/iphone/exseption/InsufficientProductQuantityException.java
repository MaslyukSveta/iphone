package com.product.iphone.exseption;

/**
 * Custom exception class to represent an insufficient product quantity situation.
 * This exception is thrown when there is not enough quantity of a product available.
 */
public class InsufficientProductQuantityException extends RuntimeException {

    /**
     * Constructs an InsufficientProductQuantityException with a message indicating the insufficient quantity for a specific product.
     *
     * @param productName The name or identifier of the product with insufficient quantity.
     */
    public InsufficientProductQuantityException(String productName) {
        super("Insufficient quantity for product: " + productName);
    }
}