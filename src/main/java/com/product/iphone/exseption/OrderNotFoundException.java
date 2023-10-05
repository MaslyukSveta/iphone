package com.product.iphone.exseption;

/**
 * Custom exception class to represent an order not found situation.
 * This exception is thrown when an order with a specific ID is not found in the system.
 */
public class OrderNotFoundException extends RuntimeException {
    /**
     * Constructs an OrderNotFoundException with a message indicating that the order with the specified ID was not found.
     *
     * @param id The unique identifier of the order that was not found.
     */
    public OrderNotFoundException(Long id) {
        super("Order with ID " + id + " not found.");
    }
}
