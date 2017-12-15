package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entity.IdentifiedEntity;
import com.netcracker.selyutin.exception.EntityNotFoundException;

import java.util.List;

public interface BaseDao<T extends IdentifiedEntity> {

    T add(T entity);

    T update(T entity);

    void delete(T entity) throws EntityNotFoundException;

    void delete(int id) throws EntityNotFoundException;

    T getById(int id) throws EntityNotFoundException;

    List<T> getAll();

}
