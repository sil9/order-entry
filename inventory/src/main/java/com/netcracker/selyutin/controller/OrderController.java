package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.OrderItem;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orders")
@Api(description = "Operations with orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Create order")
    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        Order createdOrder = orderService.create(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all orders")
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @ApiOperation(value = "Get orders by mail client")
    @GetMapping(value = "/customerEmail")
    public ResponseEntity<List<Order>> findAllCustomerOrders(@RequestParam String customerEmail) {
        List<Order> customers = orderService.findByCustomer(customerEmail);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @ApiOperation(value = "Get order by specific id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id) throws EntityNotFoundException {
        Order order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @ApiOperation(value = "Update order")
    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) throws EntityNotFoundException {
        orderService.findById(order.getId());
        orderService.update(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @ApiOperation(value = "Add item to order")
    @PostMapping(value = "/{id}/orderItems")
    public ResponseEntity<Order> addOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem) throws EntityNotFoundException {
        Order order = orderService.findById(id);
        order.getOrderItems().add(orderItem);
        orderService.update(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove item from order")
    @DeleteMapping(value = "/{id}/orderItems/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id, @PathVariable Integer orderItemId) throws EntityNotFoundException {
        Order order = orderService.findById(id);
        order.getOrderItems().removeIf(orderItem -> orderItem.getId() == orderItemId);
        orderService.update(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Remove order by specific id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) throws EntityNotFoundException {
        Order order = orderService.findById(id);
        orderService.delete(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
