package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.CategoryDao;
import com.netcracker.selyutin.entity.Category;
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
public class CategoryDaoImplTest {

    @Autowired
    private CategoryDao categoryDao;

    private Category testCategory;

    @Before
    public void setUp() throws Exception {
        testCategory = new Category();
        testCategory.setName("name");
        categoryDao.add(testCategory);
    }

    @After
    public void tearDown() throws Exception {
        categoryDao.delete(testCategory.getId());
    }

    @Test
    public void add() throws Exception {
        int id = testCategory.getId();
        assertNotNull(id);
    }

    @Test
    public void update() throws Exception {
        testCategory.setName("Changed Name");
        categoryDao.update(testCategory);
        Category categoryFromDatabase = categoryDao.getById(testCategory.getId());
        assertNotNull(categoryFromDatabase);
        assertEquals("Changed Name", testCategory.getName());
    }

    @Test
    public void delete() throws Exception {
        Category category = new Category();
        categoryDao.add(category);
        categoryDao.delete(category.getId());
        Category categoryFromDatabase = categoryDao.getById(category.getId());
        assertNull(categoryFromDatabase);
    }

    @Test
    public void getAll() throws Exception {
        List<Category> categories = categoryDao.getAll();
        assertNotNull(categories);
        assertTrue(categories.size() == 1);
    }

}