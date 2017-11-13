package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.OfferDao;
import com.netcracker.selyutin.entity.Offer;
import org.springframework.stereotype.Repository;

@Repository
public class OfferDaoImpl extends GenericDao<Offer> implements OfferDao {

    OfferDaoImpl() {
        super(Offer.class);
    }

}
