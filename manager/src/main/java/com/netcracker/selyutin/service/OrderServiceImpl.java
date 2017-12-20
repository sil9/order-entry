package com.netcracker.selyutin.service;

import com.netcracker.selyutin.client.CatalogClient;
import com.netcracker.selyutin.client.InventoryClient;
import com.netcracker.selyutin.constant.ExceptionMessage;
import com.netcracker.selyutin.constant.LoggerConstant;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.OrderItem;
import com.netcracker.selyutin.entity.Status;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    private final InventoryClient inventoryClient;
    private final CatalogClient catalogClient;

    @Autowired
    public OrderServiceImpl(InventoryClient inventoryClient, CatalogClient catalogClient) {
        this.inventoryClient = inventoryClient;
        this.catalogClient = catalogClient;
    }

    @Override
    public Order findById(int id) throws EntityNotFoundException {
        LOGGER.info("Find order by id");
        Order order = inventoryClient.findById(id);
        LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
        LOGGER.info(order);
        return order;
    }

    @Override
    public Order addOrderItem(int id, Integer offerId) throws EntityNotFoundException {
        LOGGER.info("Add item to order");
        Order order = findById(id);
        Status status = order.getStatus();
        if (!status.equals(Status.NOT_ACTIVATED)) {
            throw new UnsupportedOperationException(ExceptionMessage.FAILED_ADD_ITEM_TO_ORDER + status);
        } else {
            Offer offer = catalogClient.findOfferById(offerId);
            OrderItem orderItem = new OrderItem();
            orderItem.setName(offer.getName());
            orderItem.setPrice(offer.getPrice());
            LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
            return inventoryClient.addOrderItem(id, orderItem);
        }
    }

    @Override
    public void deleteOrderItem(int id, int orderId) throws EntityNotFoundException {
        LOGGER.info("Delete item from order");
        Order order = findById(id);
        Status status = order.getStatus();
        if (!status.equals(Status.NOT_ACTIVATED)) {
            throw new UnsupportedOperationException(ExceptionMessage.FAILED_DELETE_ITEM_FROM_ORDER + status);
        }
        if (order.getOrderItems().stream().anyMatch(item -> item.getId() == orderId)) {
            inventoryClient.deleteOrderItem(id, orderId);
            LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
        } else {
            throw new EntityNotFoundException(OrderItem.class, orderId);
        }
    }

    @Override
    public List<Order> findByCustomer(String customerMail) {
        LOGGER.info("Find orders by customer's mail");
        List<Order> orders = inventoryClient.findByCustomer(customerMail);
        LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
        LOGGER.info(orders);
        return orders;
    }

    @Override
    public List<Order> findCustomerOrdersByStatus(String customerMail, Status status) {
        LOGGER.info("Find customer orders by status");
        List<Order> orders = findByCustomer(customerMail);
        List<Order> result = orders.stream().filter(order -> order.getStatus().equals(status)).collect(Collectors.toList());
        LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
        LOGGER.info(result);
        return result;
    }

    @Override
    public Double getTotalPriceCustomerOrders(String customerMail) {
        LOGGER.info("Get total price customer's orders");
        List<Order> orders = findByCustomer(customerMail);
        double sum = orders.stream().mapToDouble(Order::getTotalPrice).sum();
        LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
        LOGGER.info(sum);
        return sum;
    }

    @Override
    public Order payForOrder(int id) throws EntityNotFoundException {
        LOGGER.info("Pay order");
        Order order = findById(id);
        Status status = order.getStatus();
        if (status.equals(Status.UNPAID) || status.equals(Status.NOT_ACTIVATED)) {
            order.setStatus(Status.PAID);
            order.setActiveDate(LocalDate.now().plusDays(30));
            inventoryClient.updateOrder(order);
            LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
            return order;
        } else {
            throw new UnsupportedOperationException(ExceptionMessage.FAILED_PAY_ORDER + status);
        }
    }

    @Override
    public List<Order> findByStatus(Status status) {
        LOGGER.info("Find orders by status");
        List<Order> orders = inventoryClient.findAll();
        List<Order> result = orders.stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());
        LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
        LOGGER.info(result);
        return result;
    }

    @Override
    public Order creteOrder(Order order) {
        LOGGER.info("Start creating order");
        Order result = inventoryClient.createOrder(order);
        LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
        LOGGER.info(result);
        return result;
    }

    @Override
    public void deleteOrder(int id) throws EntityNotFoundException {
        LOGGER.info("Start deleting order by id");
        Order order = findById(id);
        Status status = order.getStatus();
        if (status.equals(Status.NOT_ACTIVATED) || status.equals(Status.UNPAID)) {
            inventoryClient.deleteOrder(id);
            LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
        } else throw new UnsupportedOperationException(ExceptionMessage.FAILED_DELETE_ORDER + status);
    }
}
