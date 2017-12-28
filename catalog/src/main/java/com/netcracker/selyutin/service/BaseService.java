package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.IdentifiedEntity;
import com.netcracker.selyutin.exception.EntityNotFoundException;

import java.util.List;

public interface BaseService<T extends IdentifiedEntity> {

    T create(T entity);

    T update(T entity) throws EntityNotFoundException;

    T findById(int id) throws EntityNotFoundException;

    List<T> findAll();

    void delete(T entity) throws EntityNotFoundException;

}
