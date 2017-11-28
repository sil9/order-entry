package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.dao.CategoryDao;
import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl extends GenericService<Category> implements CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    CategoryServiceImpl(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offer> findOffers(Category category) {
        return categoryDao.findOffers(category);
    }
}
