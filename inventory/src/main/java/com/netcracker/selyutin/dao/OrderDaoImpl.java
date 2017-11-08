package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entities.Order;
import com.netcracker.selyutin.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OrderDaoImpl implements OrderDao{

    private EntityManager entityManager = EntityManagerUtil.getInstance().getEntityManager();

    private OrderDaoImpl() {

    }

    @Override
    public void add(Order order) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(order);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void update(Order order) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(order);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void delete(Order order) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(order);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void delete(int id) {
        Order order = getById(id);
        delete(order);
    }

    @Override
    public Order getById(int id) {
        Order order = null;
        try {
            order = entityManager.find(Order.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> resultList = null;
        try {
            CriteriaQuery<Order> criteria = entityManager.getCriteriaBuilder().createQuery(Order.class);
            criteria.select(criteria.from(Order.class));
            resultList = entityManager.createQuery(criteria).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private static class Holder {
        private final static OrderDaoImpl INSTANCE = new OrderDaoImpl();
    }

    public static OrderDaoImpl getInstance() {
        return Holder.INSTANCE;
    }
}
