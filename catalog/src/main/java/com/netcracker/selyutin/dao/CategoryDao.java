package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;

import java.util.List;

public interface CategoryDao extends BaseDao<Category> {

    List<Category> findByName(String name);

    List<Offer> findOffers(Category category);

}
