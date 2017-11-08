package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entity.Entity;

import java.util.List;

public interface BaseDao<T extends Entity> {

    void add(T entity);

    void update(T entity);

    void delete(T entity);

    void delete(int id);

    T getById(int id);

    List<T> getAll();

}
