package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Tag;

import java.util.List;

public interface OfferDao extends BaseDao<Offer> {

    List<Offer> findByTag(Tag tag);

    List<Offer> findAvailable();

    List<Offer> getByPrice(Double firstValue, Double secondValue);

}
