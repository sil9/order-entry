package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.constant.DatabaseQuery;
import com.netcracker.selyutin.dao.OfferDao;
import com.netcracker.selyutin.entity.Offer;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfferDaoImpl extends GenericDao<Offer> implements OfferDao {

    OfferDaoImpl() {
        super(Offer.class);
    }

    @Override
    public List<Offer> findByTag(int id) {
        List<Offer> offers = new ArrayList<>();
        TypedQuery<Offer> query = entityManager.createQuery(DatabaseQuery.FIND_OFFERS_BY_TAG, Offer.class);
        query.setParameter("id", id);
        try {
            offers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offers;
    }

    @Override
    public List<Offer> findAvailable() {
        List<Offer> offers = new ArrayList<>();
        TypedQuery<Offer> query = entityManager.createQuery(DatabaseQuery.FIND_AVAILABLE_OFFERS, Offer.class);
        query.setParameter("boolean", true);
        try {
            offers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offers;
    }

    @Override
    public List<Offer> getByPrice(Double firstValue, Double secondValue) {
        List<Offer> offers = new ArrayList<>();
        TypedQuery<Offer> query = entityManager.createQuery(DatabaseQuery.FIND_OFFERS_BY_PRICE, Offer.class);
        query.setParameter("firstValue", firstValue);
        query.setParameter("secondValue", secondValue);
        try {
            offers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offers;
    }
}
