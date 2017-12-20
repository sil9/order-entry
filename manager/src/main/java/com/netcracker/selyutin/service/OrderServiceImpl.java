package com.netcracker.selyutin.service;

import com.netcracker.selyutin.client.CatalogClient;
import com.netcracker.selyutin.client.InventoryClient;
import com.netcracker.selyutin.constant.ExceptionMessage;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.OrderItem;
import com.netcracker.selyutin.entity.Status;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final InventoryClient inventoryClient;
    private final CatalogClient catalogClient;

    @Autowired
    public OrderServiceImpl(InventoryClient inventoryClient, CatalogClient catalogClient) {
        this.inventoryClient = inventoryClient;
        this.catalogClient = catalogClient;
    }

    @Override
    public Order findById(int id) throws EntityNotFoundException {
        return inventoryClient.findById(id);
    }

    @Override
    public Order addOrderItem(int id, Integer offerId) throws EntityNotFoundException {
        Order order = findById(id);
        Status status = order.getStatus();
        if (!status.equals(Status.NOT_ACTIVATED)) {
            throw new UnsupportedOperationException(ExceptionMessage.FAILED_ADD_ITEM_TO_ORDER + status);
        } else {
            Offer offer = catalogClient.findOfferById(offerId);
            OrderItem orderItem = new OrderItem();
            orderItem.setName(offer.getName());
            orderItem.setPrice(offer.getPrice());
            return inventoryClient.addOrderItem(id, orderItem);
        }
    }

    @Override
    public void deleteOrderItem(int id, int orderId) throws EntityNotFoundException {
        Order order = findById(id);
        Status status = order.getStatus();
        if (!status.equals(Status.NOT_ACTIVATED)) {
            throw new UnsupportedOperationException(ExceptionMessage.FAILED_DELETE_ITEM_FROM_ORDER + status);
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
    public List<Order> findCustomerOrdersByStatus(String customerMail, Status status) {
        List<Order> orders = findByCustomer(customerMail);
        return orders.stream().filter(order -> order.getStatus().equals(status)).collect(Collectors.toList());
    }

    @Override
    public Double getTotalPriceCustomerOrders(String customerMail) {
        List<Order> orders = findByCustomer(customerMail);
        return orders.stream().mapToDouble(Order::getTotalPrice).sum();
    }

    @Override
    public Order payForOrder(int id) throws EntityNotFoundException {
        Order order = findById(id);
        Status status = order.getStatus();
        if (status.equals(Status.UNPAID) || status.equals(Status.NOT_ACTIVATED)) {
            order.setStatus(Status.PAID);
            inventoryClient.updateOrder(order);
            return order;
        } else {
            throw new UnsupportedOperationException(ExceptionMessage.FAILED_PAY_ORDER + status);
        }
    }

    @Override
    public List<Order> findByStatus(Status status) {
        List<Order> orders = inventoryClient.findAll();
        return orders.stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public Order creteOrder(Order order) {
        return inventoryClient.createOrder(order);
    }

    @Override
    public void deleteOrder(int id) throws EntityNotFoundException {
        Order order = findById(id);
        Status status = order.getStatus();
        if (status.equals(Status.NOT_ACTIVATED)) {
            inventoryClient.deleteOrder(id);
        } else throw new UnsupportedOperationException(ExceptionMessage.FAILED_DELETE_ORDER + status);
    }
}
