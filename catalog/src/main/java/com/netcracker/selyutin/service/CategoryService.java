package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;

import java.util.List;

public interface CategoryService extends BaseService<Category> {

    Category findByName(String name);

    List<Offer> findOffers(Category category);

}
