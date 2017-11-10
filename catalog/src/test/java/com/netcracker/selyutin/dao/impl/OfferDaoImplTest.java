package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.entity.Offer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;


public class OfferDaoImplTest {

    private OfferDaoImpl offerDao = OfferDaoImpl.getInstance();

    private Offer testOffer;

    @Before
    public void setUp() throws Exception {
        testOffer = new Offer();
        testOffer.setName("name");
        testOffer.setDescription("description");
    }

    @After
    public void tearDown() throws Exception {
        testOffer = null;
    }

    @Test
    public void add() throws Exception {
        offerDao.add(testOffer);
        Offer orderFromDatabase = offerDao.getById(testOffer.getId());
        offerDao.delete(orderFromDatabase);
        assertNotNull(orderFromDatabase);
        assertEquals(testOffer, orderFromDatabase);
    }

    @Test
    public void update() throws Exception {
        offerDao.add(testOffer);
        Offer newOffer = new Offer();
        newOffer.setName("new offer");
        newOffer.setId(testOffer.getId());
        offerDao.update(newOffer);
        Offer updatableOrderFromDatabase = offerDao.getById(testOffer.getId());
        assertEquals("new offer", updatableOrderFromDatabase.getName());
        offerDao.delete(testOffer.getId());
    }

    @Test
    public void delete() throws Exception {
        offerDao.add(testOffer);
        offerDao.delete(testOffer);
        Offer deletedOrder = offerDao.getById(testOffer.getId());
        assertNull(deletedOrder);
    }

    @Test
    public void getAll() throws Exception {
        offerDao.add(testOffer);
        List<Offer> orders = offerDao.getAll();
        assertNotNull(orders);
        assertFalse(orders.size() == 0);
        offerDao.delete(testOffer);
    }

    @Test
    public void getInstance() throws Exception {
        OfferDaoImpl firstInstance = OfferDaoImpl.getInstance();
        OfferDaoImpl secondInstance = OfferDaoImpl.getInstance();
        assertEquals(firstInstance.hashCode(), secondInstance.hashCode());
    }

}