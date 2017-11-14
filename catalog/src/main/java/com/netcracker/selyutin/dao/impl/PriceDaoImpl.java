package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.PriceDao;
import com.netcracker.selyutin.entity.Price;
import org.springframework.stereotype.Repository;

@Repository
public class PriceDaoImpl extends GenericDao<Price> implements PriceDao {

    PriceDaoImpl() {
        super(Price.class);
    }
}
