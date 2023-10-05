package com.product.iphone.item;

import com.product.iphone.worker.Order;
import com.product.iphone.worker.Product;
import jakarta.persistence.*;


@Entity
@Table(name = "order_items")
public class OrderItem {

    // Fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double totalCost;



    public OrderItem() {
    }

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        computeTotalCost();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        computeTotalCost();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        computeTotalCost();
    }

    public double getTotalCost() {
        return totalCost;
    }


    private void computeTotalCost() {
        if (this.product != null) {
            this.totalCost = this.product.getPrice() * this.quantity;
        } else {
            this.totalCost = 0;
        }
    }
}
