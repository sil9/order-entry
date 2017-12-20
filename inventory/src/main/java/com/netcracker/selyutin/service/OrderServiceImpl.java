package com.netcracker.selyutin.service;

import com.netcracker.selyutin.dao.OrderDao;
import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.OrderItem;
import com.netcracker.selyutin.entity.Status;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public Order create(Order order) {
        order.getOrderItems().clear();
        order.setActiveDate(null);
        order.setStatus(Status.NOT_ACTIVATED);
        orderDao.add(order);
        initializeTransientProperties(order);
        return order;
    }

    @Override
    @Transactional
    public Order update(Order order) {
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
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
    public Order findById(int id) throws EntityNotFoundException {
        Order order = orderDao.getById(id);
        if (order == null) {
            throw new EntityNotFoundException(Order.class, id);
        }
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
        if (order != null) {
            order.setTotalPrice(order.getOrderItems().stream()
                    .mapToDouble(OrderItem::getPrice)
                    .sum());
            order.setItemsCount(order.getOrderItems().size());
        }
    }
}
