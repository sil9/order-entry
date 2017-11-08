package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entities.Order;

import java.util.List;

public interface OrderDao {

    void add(Order order);

    void update(Order order);

    void delete(Order order);

    void delete(int id);

    Order getById(int id);

    List<Order> getAll();
}
