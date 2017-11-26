package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.create(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<Category>> findByName(@PathVariable("name") String name) {
        List<Category> categories = categoryService.findByName(name);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        if (id != category.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Category categoryFromDatabase = categoryService.findById(id);
        if (categoryFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.update(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id) {
        Category categoryFromDatabase = categoryService.findById(id);
        if (categoryFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(categoryService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}/offers")
    public ResponseEntity<List<Offer>> findCategoryOffers(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        if (category.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Offer> offers = categoryService.findOffers(category);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }
}
