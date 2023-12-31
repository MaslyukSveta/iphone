package com.product.iphone.service;

import com.product.iphone.exseption.InsufficientProductQuantityException;
import com.product.iphone.exseption.ProductNotFoundException;
import com.product.iphone.item.OrderItem;
import com.product.iphone.item.OrderItemDTO;
import com.product.iphone.repository.OrderRepository;
import com.product.iphone.repository.ProductRepository;
import com.product.iphone.worker.CreateOrderRequest;
import com.product.iphone.worker.Order;
import com.product.iphone.worker.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Places an order based on a request containing order items.
     *
     * @param request The CreateOrderRequest containing order item details.
     * @return The created order.
     */
    public Order placeOrder(CreateOrderRequest request) {
        Order order = new Order();
        order.setCreated(LocalDateTime.now());

        for (OrderItemDTO itemDTO : request.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + itemDTO.getProductId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            order.getItems().add(orderItem);
        }

        return orderRepository.save(order);
    }
    /**
     * Places an order based on an existing Order object.
     *
     * @param order The Order object to be saved as an order.
     * @return The created order.
     */
    @Transactional
    public Order placeOrder(Order order) {
        order.setCreated(LocalDateTime.now());
        return orderRepository.save(order);
    }

    /**
     * Marks an order as paid by updating its status in the database.
     *
     * @param orderId The ID of the order to be marked as paid.
     * @throws Exception If the order is not found or is already paid.
     */
    public void payOrder(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (!order.isPaid()) {
                order.setPaid(true);
                orderRepository.save(order);
            } else {
                throw new Exception("Order is already paid");
            }
        } else {
            throw new Exception("Order not found");
        }
    }
    /**
     * Scheduled method to delete unpaid orders older than 10 minutes.
     */
    @Scheduled(fixedRate = 600000)
    public void deleteUnpaidOrders() {
        List<Order> unpaidOrders = orderRepository.findAllByPaidFalseAndCreatedBefore(LocalDateTime.now().minusMinutes(10));
        orderRepository.deleteAll(unpaidOrders);
    }
    /**
     * Scheduled method to delete unpaid orders older than 10 minutes (alternative implementation).
     */
    @Scheduled(fixedRate = 600000)
    public void deleteUnpaidOrders1() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(10);
        List<Order> ordersToDelete = orderRepository.findAllByPaidFalseAndCreatedBefore(threshold);
        orderRepository.deleteAll(ordersToDelete);
    }


}
