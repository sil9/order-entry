package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.constant.DatabaseQuery;
import com.netcracker.selyutin.dao.CategoryDao;
import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CategoryDaoImpl extends GenericDao<Category> implements CategoryDao {

    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> findByName(String name) {
        TypedQuery<Category> query = entityManager.createQuery(DatabaseQuery.FIND_CATEGORY_BY_NAME, Category.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Offer> findOffers(Category category) {
        TypedQuery<Offer> query = entityManager.createQuery(DatabaseQuery.FIND_CATEGORY_OFFERS, Offer.class);
        query.setParameter("category", category);
        return query.getResultList();
    }
}
