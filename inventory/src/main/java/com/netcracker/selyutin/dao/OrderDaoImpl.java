package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.constant.DatabaseQuery;
import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.Status;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @Override
    public void changeOrderStatus(LocalDate date, Status previousStatus, Status futureStatus) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Order> updateCriteria = criteriaBuilder.createCriteriaUpdate(Order.class);
        Root<Order> root = updateCriteria.from(Order.class);
        updateCriteria.set(root.get("status"), futureStatus);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("status"), previousStatus));
        predicates.add(criteriaBuilder.lessThan(root.get("expirationDate"), date));
        updateCriteria.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        entityManager.createQuery(updateCriteria).executeUpdate();
    }

}
