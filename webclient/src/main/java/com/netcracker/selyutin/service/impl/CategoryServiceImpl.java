package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.client.CategoryClient;
import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryClient categoryClient;

    @Autowired
    public CategoryServiceImpl(CategoryClient categoryClient) {
        this.categoryClient = categoryClient;
    }

    @Override
    public Category findById(int id) {
        return categoryClient.findById(id);
    }

    @Override
    public void create(Category category) {
        categoryClient.create(category);
    }

    @Override
    public void update(Category category) {
        categoryClient.update(category);
    }

    @Override
    public void delete(int id) {
        categoryClient.delete(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryClient.findAll();
    }
}
