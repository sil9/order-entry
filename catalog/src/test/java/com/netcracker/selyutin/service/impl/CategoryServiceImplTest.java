package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.service.CategoryService;
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
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;
    private Category testCategory;

    @Before
    public void setUp() throws Exception {
        testCategory = new Category();
        testCategory.setName("name");
        categoryService.create(testCategory);
    }

    @After
    public void tearDown() throws Exception {
        categoryService.delete(testCategory);
    }

    @Test
    public void findByName() throws Exception {
        Category category = categoryService.findByName("name");
        assertNotNull(category);
    }

    @Test
    public void create() throws Exception {
        int id = testCategory.getId();
        assertNotEquals(0, id);
    }

    @Test
    public void update() throws Exception {
        testCategory.setName("new name");
        categoryService.update(testCategory);
        Category categoryFromDatabase = categoryService.findById(testCategory.getId());
        assertEquals("new name", categoryFromDatabase.getName());
    }

    @Test
    public void findAll() throws Exception {
        List<Category> categories = categoryService.findAll();
        assertNotNull(categories);
        assertTrue(categories.size() == 1);
    }

    @Test
    public void delete() throws Exception {
        Category category = new Category();
        categoryService.create(category);
        categoryService.delete(category);
        Category categoryFromDatabase = categoryService.findById(category.getId());
        assertNull(categoryFromDatabase);

    }

}