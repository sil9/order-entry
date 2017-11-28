package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.dao.OfferDao;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl extends GenericService<Offer> implements OfferService {

    private final OfferDao offerDao;

    @Autowired
    OfferServiceImpl(OfferDao offerDao) {
        super(offerDao);
        this.offerDao = offerDao;
    }
}
