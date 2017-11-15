package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entities.Order;
import java.util.List;

public interface OrderDao {

    Order add(Order order);

    Order update(Order order);

    void delete(Order order);

    void delete(int id);

    Order getById(int id);

    List<Order> getAll();
}
