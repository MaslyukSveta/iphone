package com.product.iphone.repository;
import com.product.iphone.worker.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByPaidFalseAndCreatedBefore(LocalDateTime localDateTime);
}
