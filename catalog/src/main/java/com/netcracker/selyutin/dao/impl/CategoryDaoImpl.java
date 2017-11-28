package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.constant.DatabaseQuery;
import com.netcracker.selyutin.dao.CategoryDao;
import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl extends GenericDao<Category> implements CategoryDao {

    CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> findByName(String name) {
        List<Category> categories = new ArrayList<>();
        TypedQuery<Category> query = entityManager.createQuery(DatabaseQuery.FIND_CATEGORY_BY_NAME, Category.class);
        query.setParameter("name", name);
        try {
            categories = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Offer> findOffers(Category category) {
        List<Offer> offerList = new ArrayList<>();
        TypedQuery<Offer> query = entityManager.createQuery(DatabaseQuery.FIND_CATEGORY_OFFERS, Offer.class);
        query.setParameter("category", category);
        try {
            offerList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offerList;
    }
}
