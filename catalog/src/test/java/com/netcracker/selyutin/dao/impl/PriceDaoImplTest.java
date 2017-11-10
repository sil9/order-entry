package com.netcracker.selyutin.dao.impl;

import com.netcracker.selyutin.entity.Price;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;


public class PriceDaoImplTest {
    
    private PriceDaoImpl priceDao = PriceDaoImpl.getInstance();

    private Price testPrice;

    @Before
    public void setUp() throws Exception {
        testPrice = new Price();
        testPrice.setValue(100);
    }

    @After
    public void tearDown() throws Exception {
        testPrice = null;
    }

    @Test
    public void add() throws Exception {
        priceDao.add(testPrice);
        Price priceFromDatabase = priceDao.getById(testPrice.getId());
        priceDao.delete(priceFromDatabase);
        assertNotNull(priceFromDatabase);
        assertEquals(testPrice, priceFromDatabase);
    }

    @Test
    public void update() throws Exception {
        priceDao.add(testPrice);
        Price newPrice = new Price();
        newPrice.setValue(200);
        newPrice.setId(testPrice.getId());
        priceDao.update(newPrice);
        Price updatablePriceFromDatabase = priceDao.getById(testPrice.getId());
        assertEquals(200, updatablePriceFromDatabase.getValue());
        priceDao.delete(testPrice.getId());
    }

    @Test
    public void delete() throws Exception {
        priceDao.add(testPrice);
        priceDao.delete(testPrice);
        Price deletedPrice = priceDao.getById(testPrice.getId());
        assertNull(deletedPrice);
    }

    @Test
    public void getAll() throws Exception {
        priceDao.add(testPrice);
        List<Price> prices = priceDao.getAll();
        assertNotNull(prices);
        assertFalse(prices.size() == 0);
        priceDao.delete(testPrice);
    }

    @Test
    public void getInstance() throws Exception {
        PriceDaoImpl firstInstance = PriceDaoImpl.getInstance();
        PriceDaoImpl secondInstance = PriceDaoImpl.getInstance();
        assertEquals(firstInstance.hashCode(), secondInstance.hashCode());
    }
    
}