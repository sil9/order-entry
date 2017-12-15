package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.PriceDao;
import com.netcracker.selyutin.entity.Price;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceDaoImplTest {

    @Autowired
    private PriceDao priceDao;

    private Price testPrice;

    @Before
    public void setUp() throws Exception {
        testPrice = new Price();
        testPrice.setValue(100);
        priceDao.add(testPrice);
    }

    @After
    public void tearDown() throws Exception {
        priceDao.delete(testPrice.getId());
    }

    @Test
    public void add() throws Exception {
        int id = testPrice.getId();
        assertNotNull(id);
    }

//    @Test
//    public void update() throws Exception {
//        testPrice.setValue(666);
//        priceDao.update(testPrice);
//        Price priceFromDatabase = priceDao.getById(testPrice.getId());
//        assertNotNull(priceFromDatabase);
//        assertEquals(666, testPrice.getValue());
//    }

    @Test
    public void delete() throws Exception {
        Price Price = new Price();
        priceDao.add(Price);
        priceDao.delete(Price.getId());
        Price priceFromDatabase = priceDao.getById(Price.getId());
        assertNull(priceFromDatabase);
    }

    @Test
    public void getAll() throws Exception {
        List<Price> categories = priceDao.getAll();
        assertNotNull(categories);
        assertTrue(categories.size() == 1);
    }
    
}