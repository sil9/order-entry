package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.dao.BaseDao;
import com.netcracker.selyutin.entity.IdentifiedEntity;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class GenericService<T extends IdentifiedEntity> implements BaseService<T> {

    private BaseDao<T> dao;

    public GenericService(BaseDao<T> dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public T create(T entity) {
        return dao.add(entity);
    }

    @Override
    @Transactional
    public T update(T entity) {
        return dao.update(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(int id) throws EntityNotFoundException {
        return dao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public void delete(T entity) throws EntityNotFoundException {
        dao.delete(entity);
    }
}
