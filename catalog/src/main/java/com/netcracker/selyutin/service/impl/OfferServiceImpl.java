package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.constant.LoggerConstant;
import com.netcracker.selyutin.dao.OfferDao;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.service.OfferService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class OfferServiceImpl extends GenericService<Offer> implements OfferService {

    private static final Logger LOGGER = LogManager.getLogger(OfferServiceImpl.class);

    private final OfferDao offerDao;

    @Autowired
    public OfferServiceImpl(OfferDao offerDao) {
        super(offerDao);
        this.offerDao = offerDao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Offer> findByTag(int id) {
        LOGGER.info("Find offers by tag id:" + id);
        List<Offer> offers = offerDao.findByTag(id);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(offers);
        return offers;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offer> findAvailable() {
        LOGGER.info("Find available offers");
        List<Offer> offers = offerDao.findAvailable();
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(offers);
        return offers;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offer> findByPrice(Double firstValue, Double secondValue) {
        LOGGER.info("Find offers by price");
        List<Offer> offers = offerDao.getByPrice(firstValue, secondValue);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(offers);
        return offers;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offer> findAllWithFilter(Map<String, Object> filters) {
        LOGGER.info("Find offers with filter");
        List<Offer> offers = offerDao.getAllWithFilter(filters);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(offers);
        return offers;
    }
}
