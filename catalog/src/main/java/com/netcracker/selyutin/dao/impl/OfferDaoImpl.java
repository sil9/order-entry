package com.netcracker.selyutin.dao.impl;

import com.netcracker.selyutin.dao.OfferDao;
import com.netcracker.selyutin.entity.Offer;


public class OfferDaoImpl extends GenericDao<Offer> implements OfferDao {

    private OfferDaoImpl() {
        super(Offer.class);
    }

    private static class Holder {
        private final static OfferDaoImpl INSTANCE = new OfferDaoImpl();
    }

    public static OfferDaoImpl getInstance() {
        return Holder.INSTANCE;
    }
}
