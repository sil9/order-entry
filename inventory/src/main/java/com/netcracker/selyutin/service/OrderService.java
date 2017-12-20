package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.exception.EntityNotFoundException;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    Order update(Order order);

    void delete(Order order);

    Order findById(int id) throws EntityNotFoundException;

    List<Order> findAll();

    List<Order> findByCustomer(String email);
}
