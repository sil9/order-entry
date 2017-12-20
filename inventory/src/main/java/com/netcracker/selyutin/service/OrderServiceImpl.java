package com.netcracker.selyutin.service;

import com.netcracker.selyutin.constant.LoggerConstant;
import com.netcracker.selyutin.dao.OrderDao;
import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.OrderItem;
import com.netcracker.selyutin.entity.Status;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public Order create(Order order) {
        LOGGER.info("Start creating the order");
        order.getOrderItems().clear();
        order.setActiveDate(null);
        order.setStatus(Status.NOT_ACTIVATED);
        Order object = orderDao.add(order);
        initializeTransientProperties(object);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(object);
        return object;
    }

    @Override
    @Transactional
    public Order update(Order order) {
        LOGGER.info("Start updating the order");
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
        Order object = orderDao.update(order);
        initializeTransientProperties(object);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(object);
        return object;
    }

    @Override
    @Transactional
    public void delete(Order order) {
        LOGGER.info("Remove order");
        if (order.getStatus().equals(Status.NOT_ACTIVATED)) {
            orderDao.delete(order);
        } else {
            order.setStatus(Status.REMOTE);
            orderDao.update(order);
        }
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(int id) throws EntityNotFoundException {
        LOGGER.info("Find order with id:" + id);
        Order order = orderDao.getById(id);
        if (order == null) {
            throw new EntityNotFoundException(Order.class, id);
        }
        initializeTransientProperties(order);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(order);
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        LOGGER.info("Find all orders");
        List<Order> orders = orderDao.getAll();
        orders.forEach(this::initializeTransientProperties);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(orders);
        return orders;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findByCustomer(String email) {
        LOGGER.info("Find orders by customer email: " + email);
        List<Order> orders = orderDao.getByCustomer(email);
        orders.forEach(this::initializeTransientProperties);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(orders);
        return orders;
    }

    private void initializeTransientProperties(Order order) {
        if (order != null) {
            order.setTotalPrice(order.getOrderItems().stream()
                    .mapToDouble(OrderItem::getPrice)
                    .sum());
            order.setItemsCount(order.getOrderItems().size());
        }
    }
}
