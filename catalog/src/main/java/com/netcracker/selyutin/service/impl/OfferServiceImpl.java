package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.dao.OfferDao;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfferServiceImpl extends GenericService<Offer> implements OfferService {

    private final OfferDao offerDao;

    @Autowired
    OfferServiceImpl(OfferDao offerDao) {
        super(offerDao);
        this.offerDao = offerDao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Offer> findByTag(Tag tag) {
        return offerDao.findByTag(tag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offer> findAvailable() {
        return offerDao.findAvailable();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offer> findByPrice(Double firstValue, Double secondValue) {
        return offerDao.getByPrice(firstValue, secondValue);
    }
}
