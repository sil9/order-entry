package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entities.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Order add(Order order) {
        try {
            entityManager.persist(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    @Transactional
    public Order update(Order order) {
        try {
            entityManager.merge(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    @Transactional
    public void delete(Order order) {
        try {
            entityManager.remove(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
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

}
