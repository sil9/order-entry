package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Category;

import java.util.List;

public interface CategoryService {

    Category findById(int id);

    void create(Category category);

    void update(Category category);

    void delete(int id);

    List<Category> findAll();
}
