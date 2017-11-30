package com.netcracker.selyutin.service;

import com.netcracker.selyutin.dao.OrderDao;
import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Autowired
    OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public Order create(Order order) {
        order.setCreationDate(LocalDate.now());
        orderDao.add(order);
        initializeTransientProperties(order);
        return order;
    }

    @Override
    @Transactional
    public Order update(Order order) {
        orderDao.update(order);
        initializeTransientProperties(order);
        return order;
    }

    @Override
    @Transactional
    public void delete(Order order) {
        orderDao.delete(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(int id) {
        Order order = orderDao.getById(id);
        initializeTransientProperties(order);
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        List<Order> orders = orderDao.getAll();
        orders.forEach(this::initializeTransientProperties);
        return orders;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findByCustomer(String email) {
        List<Order> orders = orderDao.getByCustomer(email);
        orders.forEach(this::initializeTransientProperties);
        return orders;
    }

    private void initializeTransientProperties(Order order) {
        order.setTotalPrice(order.getOrderItems().stream()
                .mapToDouble(OrderItem::getPrice)
                .sum());
        order.setItemsCount(order.getOrderItems().size());
    }
}
