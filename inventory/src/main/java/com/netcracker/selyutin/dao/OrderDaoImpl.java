package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.constant.DatabaseQuery;
import com.netcracker.selyutin.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order add(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        entityManager.merge(order);
        return order;
    }

    @Override
    public void delete(Order order) {
        entityManager.remove(order);
    }

    @Override
    public Order getById(int id) {
        Order order;
        order = entityManager.find(Order.class, id);
        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders;
        CriteriaQuery<Order> criteria = entityManager.getCriteriaBuilder().createQuery(Order.class);
        criteria.select(criteria.from(Order.class));
        orders = entityManager.createQuery(criteria).getResultList();
        return orders;
    }

    @Override
    public List<Order> getByCustomer(String email) {
        List<Order> orders;
        TypedQuery<Order> query = entityManager.createQuery(DatabaseQuery.FIND_ALL_CUSTOMER_ORDERS, Order.class);
        query.setParameter("email", email);
        orders = query.getResultList();
        return orders;
    }

}
