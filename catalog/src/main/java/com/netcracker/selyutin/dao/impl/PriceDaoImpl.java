package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.PriceDao;
import com.netcracker.selyutin.entities.Price;

public class PriceDaoImpl extends GenericDao<Price> implements PriceDao {

    private PriceDaoImpl() {
        super(Price.class);
    }

    private static class Holder {
        private final static PriceDaoImpl INSTANCE = new PriceDaoImpl();
    }

    public PriceDaoImpl getInstance() {
        return Holder.INSTANCE;
    }
}
