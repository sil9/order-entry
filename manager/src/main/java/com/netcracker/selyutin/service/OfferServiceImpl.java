package com.netcracker.selyutin.service;

import com.netcracker.selyutin.client.CatalogClient;
import com.netcracker.selyutin.constant.LoggerConstant;
import com.netcracker.selyutin.entity.Offer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OfferServiceImpl implements OfferService {

    private static final Logger LOGGER = LogManager.getLogger(OfferServiceImpl.class);

    private final CatalogClient catalogClient;

    @Autowired
    public OfferServiceImpl(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @Override
    public List<Offer> findWithFilter(Map<String, Object> params) {
        LOGGER.info("Start search offers with filter");
        List<Offer> offers = catalogClient.findAllOffer(params);
        LOGGER.info(LoggerConstant.REQUEST_SUCCESSFUL);
        LOGGER.info(offers);
        return offers;
    }
}
