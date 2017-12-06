package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.OrderItem;
import com.netcracker.selyutin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        Order createdOrder = orderService.create(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/customerEmail")
    public ResponseEntity<List<Order>> findAllCustomerOrders(@RequestParam String customerEmail) {
        List<Order> customers = orderService.findByCustomer(customerEmail);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order orderFromDatabase = orderService.findById(order.getId());
        if (orderFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.update(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/orderItems")
    public ResponseEntity<Order> addOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem) {
        Order order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.getOrderItems().add(orderItem);
        orderService.update(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}/orderItems/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id, @PathVariable Integer orderItemId) {
        Order order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.getOrderItems().removeIf(orderItem -> orderItem.getId() == orderItemId);
        orderService.update(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.delete(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
