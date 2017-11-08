package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.CategoryDao;
import com.netcracker.selyutin.entities.Category;

public class CategoryDaoImpl extends GenericDao<Category> implements CategoryDao {

    private CategoryDaoImpl() {
        super(Category.class);
    }

    private static class Holder {
        private final static CategoryDaoImpl INSTANCE = new CategoryDaoImpl();
    }

    public static CategoryDaoImpl getInstance() {
        return Holder.INSTANCE;
    }
}
