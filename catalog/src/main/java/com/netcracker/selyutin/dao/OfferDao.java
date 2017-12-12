package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entity.Offer;

import java.util.List;
import java.util.Map;

public interface OfferDao extends BaseDao<Offer> {

    List<Offer> findByTag(int id);

    List<Offer> findAvailable();

    List<Offer> getByPrice(Double firstValue, Double secondValue);

    List<Offer> getAllWithFilter(Map<String, Object> filters);
}
