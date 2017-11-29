package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Offer;

import java.util.List;

public interface OfferService extends BaseService<Offer> {

    List<Offer> findByTag(int id);

    List<Offer> findAvailable();

    List<Offer> findByPrice(Double firstValue, Double secondValue);

}
