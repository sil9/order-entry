package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.Status;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao {

    Order add(Order order);

    Order update(Order order);

    void delete(Order order);

    Order getById(int id);

    List<Order> getAll();

    List<Order> getByCustomer(String email);

    void changeOrderStatus(LocalDate date, Status previousStatus, Status futureStatus);
}
