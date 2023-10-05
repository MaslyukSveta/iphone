package com.product.iphone.service;

import com.product.iphone.repository.OrderRepository;
import com.product.iphone.worker.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduledTasks {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Scheduled task to delete unpaid orders older than 10 minutes.
     */
    @Scheduled(fixedRate = 600000)
    public void deleteUnpaidOrders() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(10);
        List<Order> ordersToDelete = orderRepository.findAllByPaidFalseAndCreatedBefore(threshold);
        orderRepository.deleteAll(ordersToDelete);
    }
}