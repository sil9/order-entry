package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.dao.BaseDao;
import com.netcracker.selyutin.entity.IdentifiedEntity;
import com.netcracker.selyutin.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class GenericService<T extends IdentifiedEntity> implements BaseService<T> {

    private BaseDao<T> dao;

    GenericService(BaseDao<T> dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public T create(T entity) {
        dao.add(entity);
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) {
        dao.update(entity);
        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(int id) {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public void delete(T entity) {
        dao.delete(entity);
    }
}
