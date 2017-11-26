package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.constant.DatabaseQuery;
import com.netcracker.selyutin.dao.CategoryDao;
import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl extends GenericDao<Category> implements CategoryDao {

    CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public Category add(Category entity) {
        return super.add(entity);
    }

    @Override
    public List<Category> findByName(String name) {
        List<Category> categories = new ArrayList<>();
        Query query = entityManager.createQuery(DatabaseQuery.FIND_CATEGORY_BY_NAME);
        query.setParameter("name", name);
        try {
            categories = (List<Category>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Offer> findOffers(Category category) {
        List<Offer> offerList = new ArrayList<>();
        Query query = entityManager.createQuery(DatabaseQuery.FIND_CATEGORY_OFFERS);
        query.setParameter("category", category);
        try {
            offerList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offerList;
    }
}
