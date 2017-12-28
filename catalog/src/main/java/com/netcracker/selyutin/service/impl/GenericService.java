package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.constant.LoggerConstant;
import com.netcracker.selyutin.dao.BaseDao;
import com.netcracker.selyutin.entity.IdentifiedEntity;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.service.BaseService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class GenericService<T extends IdentifiedEntity> implements BaseService<T> {

    private static final Logger LOGGER = LogManager.getLogger(GenericService.class);

    private BaseDao<T> dao;

    public GenericService(BaseDao<T> dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public T create(T entity) {
        LOGGER.info("Started creating the entity in the database");
        T object = dao.add(entity);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(object);
        return object;
    }

    @Override
    @Transactional
    public T update(T entity) throws EntityNotFoundException {
        LOGGER.info("Started updating the entity in the database");
        T object = dao.update(entity);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(int id) throws EntityNotFoundException {
        LOGGER.info("Search the entity in the database");
        T object = dao.getById(id);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(object);
        return object;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        LOGGER.info("Search entities in the database");
        List<T> entities = dao.getAll();
        LOGGER.info((LoggerConstant.TRANSACTION_SUCCEEDED));
        LOGGER.info(entities);
        return entities;
    }

    @Override
    @Transactional
    public void delete(T entity) throws EntityNotFoundException {
        LOGGER.info("Remove entity from the database");
        dao.delete(entity);
        LOGGER.info((LoggerConstant.TRANSACTION_SUCCEEDED));
    }
}
