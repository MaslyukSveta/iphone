package com.product.iphone.controller;
import com.product.iphone.worker.CreateOrderRequest;
import com.product.iphone.worker.Order;
import com.product.iphone.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<String> payOrder(@PathVariable Long orderId) {
        try {
            orderService.payOrder(orderId);
            return new ResponseEntity<>("Order paid successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody @Valid CreateOrderRequest request) {
        Order order = orderService.placeOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


}
