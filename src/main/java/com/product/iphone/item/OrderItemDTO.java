package com.product.iphone.item;

import java.util.List;

public class OrderItemDTO {

    private Long productId;
    private Integer quantity;


    public OrderItemDTO() {}

    public OrderItemDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // ToString method for better debugging (optional)
    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
