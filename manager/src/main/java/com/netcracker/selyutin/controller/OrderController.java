package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.OrderItem;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order returnedOrder = orderService.creteOrder(order);
        return new ResponseEntity<>(returnedOrder, HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}/orderItems")
    public ResponseEntity<Order> addOrderItem(@PathVariable Integer id, @RequestParam Integer offerId) throws EntityNotFoundException {
        Order order = orderService.addOrderItem(id, offerId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}/orderItems/{orderId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id, @PathVariable Integer orderId) throws EntityNotFoundException {
        orderService.deleteOrderItem(id, orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAllCustomerOrders(@RequestParam String customerMail) {
        List<Order> orders = orderService.findByCustomer(customerMail);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{customerMail}")
    public ResponseEntity<List<Order>> findCustomerOrdersByPaid(@PathVariable String customerMail, @RequestParam Boolean paid) {
        List<Order> orders = orderService.findCustomerOrdersByPaid(customerMail, paid);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{customerMail}/totalPrice")
    public ResponseEntity<Double> getTotalPriceCustomerOrders(@PathVariable String customerMail) {
        Double totalPrice = orderService.getTotalPriceCustomerOrders(customerMail);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/pay")
    public ResponseEntity<Order> payForOrder(@PathVariable Integer id) throws EntityNotFoundException {
        Order order = orderService.payForOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(value = "/paymentStatus")
    public ResponseEntity<List<Order>> findOrdersByPaymentStatus(@RequestParam Boolean paymentStatus) {
        List<Order> orders = orderService.findByPaymentStatus(paymentStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Integer id) throws EntityNotFoundException {
        Order order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
