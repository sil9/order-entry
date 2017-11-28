package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.IdentifiedEntity;

import java.util.List;

public interface BaseService<T extends IdentifiedEntity> {

    T create(T entity);

    T update(T entity);

    T findById(int id);

    List<T> findAll();

    void delete(T entity);

}
