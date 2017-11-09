package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entities.Entity;
import com.netcracker.selyutin.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class GenericDao<T extends Entity> implements BaseDao<T> {

    private EntityManager entityManager = EntityManagerUtil.getInstance().getEntityManager();
    private Class<T> tClass;

    GenericDao(Class<T> tClass) {
        this.tClass = tClass;
    }

    public void add(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void update(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void delete(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void delete(int id) {
        T entity = getById(id);
        delete(entity);
    }

    public T getById(int id) {
        T entity = null;
        try {
            entity = entityManager.find(tClass, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public List<T> getAll() {
        List<T> resultList = null;
        try {
            CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(tClass);
            criteria.select(criteria.from(tClass));
            resultList = entityManager.createQuery(criteria).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
