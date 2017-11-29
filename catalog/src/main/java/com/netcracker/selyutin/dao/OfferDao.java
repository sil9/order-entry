package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entity.Offer;

import java.util.List;

public interface OfferDao extends BaseDao<Offer> {

    List<Offer> findByTag(int id);

    List<Offer> findAvailable();

    List<Offer> getByPrice(Double firstValue, Double secondValue);

}
