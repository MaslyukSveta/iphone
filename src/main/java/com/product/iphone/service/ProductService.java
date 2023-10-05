package com.product.iphone.service;

import com.product.iphone.worker.Product;
import com.product.iphone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    /**
     * Adds a new product to the database.
     *
     * @param product The product to be added.
     * @return The saved product with an updated ID (if generated).
     */
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    /**
     * Retrieves a product by its unique ID.
     *
     * @param id The ID of the product to retrieve.
     * @return An Optional containing the product if found, or an empty Optional if not found.
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Retrieves a list of all products available in the database.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}
