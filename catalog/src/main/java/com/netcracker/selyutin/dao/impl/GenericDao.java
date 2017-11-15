package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.BaseDao;
import com.netcracker.selyutin.entity.IdentifiedEntity;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class GenericDao<T extends IdentifiedEntity> implements BaseDao<T> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> tClass;

    GenericDao(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Transactional
    public T add(T entity) {
        try {
            entityManager.persist(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Transactional
    public T update(T entity) {
        try {
            entityManager.merge(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Transactional
    public void delete(T entity) {
        try {
            entityManager.remove(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
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
