package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Offer;

import java.util.List;
import java.util.Map;

public interface OfferService {

    Offer findById(int id);

    void create(Offer offer);

    void update(Offer offer);

    void delete(int id);

    List<Offer> findWithFilter(Map<String, Object> params);

    List<Offer> findRelatedOffers(int offerId);

}
