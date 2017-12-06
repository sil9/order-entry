package com.netcracker.selyutin.service;

import com.netcracker.selyutin.client.InventoryClient;
import com.netcracker.selyutin.constant.ExceptionMessage;
import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.OrderItem;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final InventoryClient inventoryClient;

    @Autowired
    OrderServiceImpl(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @Override
    public Order findById(int id) throws EntityNotFoundException {
        return inventoryClient.findById(id);
    }

    @Override
    public Order addOrderItem(int id, OrderItem orderItem) throws EntityNotFoundException {
        Order order = findById(id);
        if (order.isPaymentStatus()) {
            throw new UnsupportedOperationException(ExceptionMessage.FAILED_ADD_ITEM_TO_PAID_ORDER);
        } else {
            return inventoryClient.addOrderItem(id, orderItem);
        }
    }

    @Override
    public void deleteOrderItem(int id, int orderId) throws EntityNotFoundException {
        Order order = findById(id);
        if (order.isPaymentStatus()) {
            throw new UnsupportedOperationException(ExceptionMessage.FAILED_DELETE_ITEM_TO_PAID_ORDER);
        }
        if (order.getOrderItems().stream().anyMatch(item -> item.getId() == orderId)) {
            inventoryClient.deleteOrderItem(id, orderId);
        } else {
            throw new EntityNotFoundException(OrderItem.class, orderId);
        }
    }

    @Override
    public List<Order> findByCustomer(String customerMail) {
        return inventoryClient.findByCustomer(customerMail);
    }

    @Override
    public List<Order> findCustomerOrdersByPaid(String customerMail, boolean paid) {
        List<Order> orders = findByCustomer(customerMail);
        return orders.stream().filter(order -> order.isPaymentStatus() == paid).collect(Collectors.toList());
    }

    @Override
    public Double getTotalPriceCustomerOrders(String customerMail) {
        List<Order> orders = findByCustomer(customerMail);
        return orders.stream().mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public Order payForOrder(int id) throws EntityNotFoundException {
        Order order = findById(id);
        if (order.isPaymentStatus()) {
            throw new UnsupportedOperationException(ExceptionMessage.FAILED_PAY_PAID_ORDER);
        } else {
            order.setPaymentStatus(true);
            inventoryClient.updateOrder(order);
            return order;
        }
    }

    @Override
    public List<Order> findByPaymentStatus(boolean paymentStatus) {
        List<Order> orders = inventoryClient.findAll();
        return orders.stream()
                .filter(order -> order.isPaymentStatus() == paymentStatus)
                .collect(Collectors.toList());
    }

    @Override
    public Order creteOrder(Order order) {
        return inventoryClient.createOrder(order);
    }
}
