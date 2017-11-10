package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.entity.Tag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;


public class TagDaoImplTest {

    private TagDaoImpl tagDao = TagDaoImpl.getInstance();

    private Tag testTag;

    @Before
    public void setUp() throws Exception {
        testTag = new Tag();
        testTag.setSign("sing");
    }

    @After
    public void tearDown() throws Exception {
        testTag = null;
    }

    @Test
    public void add() throws Exception {
        tagDao.add(testTag);
        Tag tagFromDatabase = tagDao.getById(testTag.getId());
        tagDao.delete(tagFromDatabase);
        assertNotNull(tagFromDatabase);
        assertEquals(testTag, tagFromDatabase);
    }

    @Test
    public void update() throws Exception {
        tagDao.add(testTag);
        Tag newTag = new Tag();
        newTag.setSign("new sign");
        newTag.setId(testTag.getId());
        tagDao.update(newTag);
        Tag updatableTagFromDatabase = tagDao.getById(testTag.getId());
        assertEquals("new sign", updatableTagFromDatabase.getSign());
        tagDao.delete(testTag.getId());
    }

    @Test
    public void delete() throws Exception {
        tagDao.add(testTag);
        tagDao.delete(testTag);
        Tag deletedTag = tagDao.getById(testTag.getId());
        assertNull(deletedTag);
    }

    @Test
    public void getAll() throws Exception {
        tagDao.add(testTag);
        List<Tag> tags = tagDao.getAll();
        assertNotNull(tags);
        assertFalse(tags.size() == 0);
        tagDao.delete(testTag);
    }

    @Test
    public void getInstance() throws Exception {
        TagDaoImpl firstInstance = TagDaoImpl.getInstance();
        TagDaoImpl secondInstance = TagDaoImpl.getInstance();
        assertEquals(firstInstance.hashCode(), secondInstance.hashCode());
    }

}