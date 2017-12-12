package com.netcracker.selyutin.service;

import com.netcracker.selyutin.client.CatalogClient;
import com.netcracker.selyutin.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OfferServiceImpl implements OfferService {

    private final CatalogClient catalogClient;

    @Autowired
    public OfferServiceImpl(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @Override
    public List<Offer> findWithFilter(Map<String, Object> params) {
        return catalogClient.findAllOffer(params);
    }
}
