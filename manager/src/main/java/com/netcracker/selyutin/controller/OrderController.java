package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.Status;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orders")
@Api(description = "Operations with orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Create order")
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order) {
        Order returnedOrder = orderService.creteOrder(order);
        return new ResponseEntity<>(returnedOrder, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Add item to order")
    @PostMapping(value = "/{id}/orderItems")
    public ResponseEntity<Order> addOrderItem(@PathVariable Integer id, @RequestParam Integer offerId) throws EntityNotFoundException {
        Order order = orderService.addOrderItem(id, offerId);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove item from order")
    @DeleteMapping(value = "/{id}/orderItems/{orderId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id, @PathVariable Integer orderId) throws EntityNotFoundException {
        orderService.deleteOrderItem(id, orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get orders by mail client")
    @GetMapping
    public ResponseEntity<List<Order>> findAllCustomerOrders(@RequestParam String customerMail) {
        List<Order> orders = orderService.findByCustomer(customerMail);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @ApiOperation(value = "Get client's orders by paid status")
    @GetMapping(value = "/search")
    public ResponseEntity<List<Order>> findCustomerOrdersByPaid(@RequestParam String customerMail, @RequestParam Status status) {
        List<Order> orders = orderService.findCustomerOrdersByStatus(customerMail, status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @ApiOperation(value = "Get total client's price")
    @GetMapping(value = "/customer/{customerMail}/totalPrice")
    public ResponseEntity<Double> getTotalPriceCustomerOrders(@PathVariable String customerMail) {
        Double totalPrice = orderService.getTotalPriceCustomerOrders(customerMail);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    @ApiOperation(value = "Pay order")
    @PutMapping(value = "/{id}/pay")
    public ResponseEntity<Order> payForOrder(@PathVariable Integer id) throws EntityNotFoundException {
        Order order = orderService.payForOrder(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @ApiOperation(value = "Get order by specific id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Integer id) throws EntityNotFoundException {
        Order order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @ApiOperation(value = "Remove order by specific id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) throws EntityNotFoundException {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
