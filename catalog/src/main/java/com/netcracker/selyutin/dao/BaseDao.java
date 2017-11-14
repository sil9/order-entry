package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entity.IdentifiedEntity;
import java.util.List;

public interface BaseDao<T extends IdentifiedEntity> {

    T add(T entity);

    T update(T entity);

    void delete(T entity);

    void delete(int id);

    T getById(int id);

    List<T> getAll();

}
