package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.Status;
import com.netcracker.selyutin.exception.EntityNotFoundException;

import java.util.List;

public interface OrderService {

    Order findById(int id) throws EntityNotFoundException;

    Order addOrderItem(int id, Integer offerId) throws EntityNotFoundException, UnsupportedOperationException;

    void deleteOrderItem(int id, int orderId) throws EntityNotFoundException;

    List<Order> findByCustomer(String customerMail);

    List<Order> findCustomerOrdersByStatus(String customerMail, Status status);

    Double getTotalPriceCustomerOrders(String customerMail);

    Order payForOrder(int id) throws EntityNotFoundException;

    List<Order> findByStatus(Status status);

    Order creteOrder(Order order);

    void deleteOrder(int id) throws EntityNotFoundException;
}
