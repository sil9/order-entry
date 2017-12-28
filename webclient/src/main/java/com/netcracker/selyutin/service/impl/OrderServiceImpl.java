package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.client.OrderClient;
import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.Status;
import com.netcracker.selyutin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderClient orderClient;

    @Autowired
    public OrderServiceImpl(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @Override
    public List<Order> findCustomerOrdersByStatus(String customerMail, Status status) {
        return orderClient.findCustomerOrdersByStatus(customerMail, status);
    }

    @Override
    public Order createOrder(Order order) {
        return orderClient.createOrder(order);
    }

    @Override
    public void payOrder(int id) {
        orderClient.payOrder(id);
    }

    @Override
    public void deleteOrder(int id) {
        orderClient.deleteOrder(id);
    }

    @Override
    public void deleteOrderItem(int orderId, int itemId) {
        orderClient.deleteOrderItem(orderId, itemId);
    }

    @Override
    public void addOrderItem(int orderId, int offerId) {
        orderClient.addOrderItem(orderId, offerId);
    }
}
