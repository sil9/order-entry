package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Tag;

import java.util.List;

public interface OfferService extends BaseService<Offer> {

    List<Offer> findByTag(Tag tag);

    List<Offer> findAvailable();

    List<Offer> findByPrice(Double firstValue, Double secondValue);

}
