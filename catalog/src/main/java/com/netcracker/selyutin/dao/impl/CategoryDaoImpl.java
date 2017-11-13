package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.CategoryDao;
import com.netcracker.selyutin.entity.Category;
import org.springframework.stereotype.Repository;

@Repository()
public class CategoryDaoImpl extends GenericDao<Category> implements CategoryDao {

    CategoryDaoImpl() {
        super(Category.class);
    }

}
