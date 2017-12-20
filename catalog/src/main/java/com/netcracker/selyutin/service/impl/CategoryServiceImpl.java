package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.constant.LoggerConstant;
import com.netcracker.selyutin.dao.CategoryDao;
import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.service.CategoryService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl extends GenericService<Category> implements CategoryService {

    private static final Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findByName(String name) {
        LOGGER.info("Find categories by name: " + name);
        List<Category> categories = categoryDao.findByName(name);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(categories);
        return categories;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offer> findOffers(Category category) {
        LOGGER.info("Find offers by category: " + category);
        List<Offer> offers = categoryDao.findOffers(category);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(offers);
        return offers;
    }
}
