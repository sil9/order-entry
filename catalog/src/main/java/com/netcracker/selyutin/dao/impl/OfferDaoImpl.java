package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.constant.DatabaseQuery;
import com.netcracker.selyutin.dao.OfferDao;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class OfferDaoImpl extends GenericDao<Offer> implements OfferDao {

    OfferDaoImpl() {
        super(Offer.class);
    }

    @Override
    public List<Offer> findByTag(Tag tag) {
        Query query = entityManager.createQuery(DatabaseQuery.FIND_OFFERS_BY_TAG);
        query.setParameter("tag", tag);
        return (List<Offer>) query.getResultList();
    }

    @Override
    public List<Offer> findAvailable() {
        Query query = entityManager.createQuery(DatabaseQuery.FIND_AVAILABLE_OFFERS);
        return (List<Offer>) query.getResultList();
    }

    @Override
    public List<Offer> getByPrice(Double firstValue, Double secondValue) {
        Query query = entityManager.createQuery(DatabaseQuery.FIND_OFFERS_BY_PRICE);
        query.setParameter("firstValue", firstValue);
        query.setParameter("secondValue", secondValue);
        return (List<Offer>) query.getResultList();
    }
}
