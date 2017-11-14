package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.TagDao;
import com.netcracker.selyutin.entity.Tag;
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
public class TagDaoImplTest {

    @Autowired
    private TagDao tagDao;

    private Tag testTag;

    @Before
    public void setUp() throws Exception {
        testTag = new Tag();
        testTag.setSign("sign");
        tagDao.add(testTag);
    }

    @After
    public void tearDown() throws Exception {
        tagDao.delete(testTag.getId());
    }

    @Test
    public void add() throws Exception {
        int id = testTag.getId();
        assertNotNull(id);
    }

    @Test
    public void update() throws Exception {
        testTag.setSign("Changed Sign");
        tagDao.update(testTag);
        Tag tagFromDatabase = tagDao.getById(testTag.getId());
        assertNotNull(tagFromDatabase);
        assertEquals("Changed Sign", testTag.getSign());
    }

    @Test
    public void delete() throws Exception {
        Tag Tag = new Tag();
        tagDao.add(Tag);
        tagDao.delete(Tag.getId());
        Tag tagFromDatabase = tagDao.getById(Tag.getId());
        assertNull(tagFromDatabase);
    }

    @Test
    public void getAll() throws Exception {
        List<Tag> categories = tagDao.getAll();
        assertNotNull(categories);
        assertTrue(categories.size() == 1);
    }

}