package com.product.iphone.exseption;


/**
 * Custom exception class to represent a product not found situation.
 * This exception is thrown when a product with a specific ID is not found in the system.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructs a ProductNotFoundException with a message indicating that the product with the specified ID was not found.
     *
     * @param id The unique identifier (ID) of the product that was not found.
     */
    public ProductNotFoundException(String id) {
        super("Product with ID " + id + " not found.");
    }
}