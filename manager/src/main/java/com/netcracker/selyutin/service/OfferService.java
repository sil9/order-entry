package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Offer;

import java.util.List;
import java.util.Map;

public interface OfferService {

    List<Offer> findWithFilter(Map<String, Object> params);

}
