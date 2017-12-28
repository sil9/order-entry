package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.Status;

import java.util.List;

public interface OrderService {

    List<Order> findCustomerOrdersByStatus(String customerMail, Status status);

    Order createOrder(Order order);

    void payOrder(int id);

    void deleteOrder(int id);

    void deleteOrderItem(int orderId, int itemId);

    void addOrderItem(int orderId, int offerId);
}
