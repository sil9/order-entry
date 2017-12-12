package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Offer;

import java.util.List;
import java.util.Map;

public interface OfferService extends BaseService<Offer> {

    List<Offer> findByTag(int id);

    List<Offer> findAvailable();

    List<Offer> findByPrice(Double firstValue, Double secondValue);

    List<Offer> findAllWithFilter(Map<String, Object> filters);
}
