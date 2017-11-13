package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.OfferDao;
import com.netcracker.selyutin.entity.Offer;
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
public class OfferDaoImplTest {

    @Autowired
    private OfferDao offerDao;

    private Offer testOffer;

    @Before
    public void setUp() throws Exception {
        testOffer = new Offer();
        testOffer.setName("name");
        offerDao.add(testOffer);
    }

    @After
    public void tearDown() throws Exception {
        offerDao.delete(testOffer.getId());
    }

    @Test
    public void add() throws Exception {
        int id = testOffer.getId();
        assertNotNull(id);
    }

    @Test
    public void update() throws Exception {
        testOffer.setName("Changed Name");
        offerDao.update(testOffer);
        Offer offerFromDatabase = offerDao.getById(testOffer.getId());
        assertNotNull(offerFromDatabase);
        assertEquals("Changed Name", testOffer.getName());
    }

    @Test
    public void delete() throws Exception {
        Offer Offer = new Offer();
        offerDao.add(Offer);
        offerDao.delete(Offer.getId());
        Offer offerFromDatabase = offerDao.getById(Offer.getId());
        assertNull(offerFromDatabase);
    }

    @Test
    public void getAll() throws Exception {
        List<Offer> categories = offerDao.getAll();
        assertNotNull(categories);
        assertTrue(categories.size() == 1);
    }

}