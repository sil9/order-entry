package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.BaseDao;
import com.netcracker.selyutin.entity.IdentifiedEntity;
import com.netcracker.selyutin.exception.EntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class GenericDao<T extends IdentifiedEntity> implements BaseDao<T> {

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> tClass;

    public GenericDao(Class<T> tClass) {
        this.tClass = tClass;
    }

    public T add(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    public void delete(T entity) throws EntityNotFoundException {
        entityManager.remove(getById(entity.getId()));
    }

    public void delete(int id) throws EntityNotFoundException {
        T entity = getById(id);
        delete(entity);
    }

    public T getById(int id) throws EntityNotFoundException {
        T entity = entityManager.find(tClass, id);
        if (entity == null) {
            throw new EntityNotFoundException(tClass, id);
        }
        return entity;
    }

    public List<T> getAll() {
        CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(tClass);
        criteria.select(criteria.from(tClass));
        return entityManager.createQuery(criteria).getResultList();
    }
}
