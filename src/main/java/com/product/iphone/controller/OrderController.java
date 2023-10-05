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

    /**
     * Place an order by creating a new order.
     *
     * @param order The order to be placed. Should be a valid Order object.
     * @return The placed order.
     */
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    /**
     * Pay for an existing order by its unique ID.
     *
     * @param orderId The ID of the order to be paid.
     * @return A ResponseEntity with a success message if payment is successful, or a bad request response if an error occurs.
     */
    @PostMapping("/{id}/pay")
    public ResponseEntity<String> payOrder(@PathVariable Long orderId) {
        try {
            orderService.payOrder(orderId);
            return new ResponseEntity<>("Order paid successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * Place an order by creating a new order based on the request.
     *
     * @param request The request containing order items. Should be a valid CreateOrderRequest object.
     * @return A ResponseEntity containing the placed order with a CREATED status.
     */
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody @Valid CreateOrderRequest request) {
        Order order = orderService.placeOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


}
