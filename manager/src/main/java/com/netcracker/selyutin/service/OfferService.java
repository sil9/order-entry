package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Offer;

import java.util.List;

public interface OfferService {

    List<Offer> findWithFilter(String categoryName, Double minPrice, Double maxPrice, List<String> tagsNames);

}
