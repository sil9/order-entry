package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.entity.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;


public class CategoryDaoImplTest {
    
    private CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
    
    private Category testCategory;

    @Before
    public void setUp() throws Exception {
        testCategory = new Category();
        testCategory.setName("name");
    }

    @After
    public void tearDown() throws Exception {
        testCategory = null;
    }

    @Test
    public void add() throws Exception {
        categoryDao.add(testCategory);
        Category categoryFromDatabase = categoryDao.getById(testCategory.getId());
        categoryDao.delete(categoryFromDatabase);
        assertNotNull(categoryFromDatabase);
        assertEquals(testCategory, categoryFromDatabase);
    }

    @Test
    public void update() throws Exception {
        categoryDao.add(testCategory);
        Category newCategory = new Category();
        newCategory.setName("new Category");
        newCategory.setId(testCategory.getId());
        categoryDao.update(newCategory);
        Category updatableCategoryFromDatabase = categoryDao.getById(testCategory.getId());
        assertEquals("new Category", updatableCategoryFromDatabase.getName());
        categoryDao.delete(testCategory.getId());
    }

    @Test
    public void delete() throws Exception {
        categoryDao.add(testCategory);
        categoryDao.delete(testCategory);
        Category deletedCategory = categoryDao.getById(testCategory.getId());
        assertNull(deletedCategory);
    }

    @Test
    public void getAll() throws Exception {
        categoryDao.add(testCategory);
        List<Category> categories = categoryDao.getAll();
        assertNotNull(categories);
        assertFalse(categories.size() == 0);
        categoryDao.delete(testCategory);
    }

    @Test
    public void getInstance() throws Exception {
        CategoryDaoImpl firstInstance = CategoryDaoImpl.getInstance();
        CategoryDaoImpl secondInstance = CategoryDaoImpl.getInstance();
        assertEquals(firstInstance.hashCode(), secondInstance.hashCode());
    }

}