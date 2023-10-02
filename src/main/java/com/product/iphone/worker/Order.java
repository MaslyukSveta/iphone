package com.product.iphone.worker;

import com.product.iphone.item.OrderItem;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private boolean paid;

    @Column(nullable = false)
    private double totalAmount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order", orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
        this.created = LocalDateTime.now();
        this.paid = false;
        this.totalAmount = 0;
    }
    @PrePersist
    protected void onCreate() {
        if (created == null) {
            created = LocalDateTime.now();
        }

    }
    private List<OrderItem> items = new ArrayList<>();

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        if(orderItem != null) {
            orderItems.add(orderItem);
            orderItem.setOrder(this);
            totalAmount += orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
    }

    public void removeOrderItem(OrderItem orderItem) {
        if(orderItem != null) {
            orderItems.remove(orderItem);
            totalAmount -= orderItem.getProduct().getPrice() * orderItem.getQuantity();
        }
    }

    public void setCreated(LocalDateTime now) {
        if (this.created == null) {
            this.created = now;
        } else {
            throw new IllegalStateException("Creation time can't be reset.");
        }
    }



}
