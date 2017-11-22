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
        Category categoryFromDatabase = categoryService.findByName(category.getName());
        if (categoryFromDatabase != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Category createdCategory = categoryService.create(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") String id) {
        Category category = categoryService.findById(Integer.parseInt(id));
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(value = "/find/{name}")
    public ResponseEntity<Category> findByName(@PathVariable("name") String name) {
        Category category = categoryService.findByName(name);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        Category categoryFromDatabase = categoryService.findById(category.getId());
        if (categoryFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.update(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCategory(@RequestBody Category category) {
        Category categoryFromDatabase = categoryService.findById(category.getId());
        if (categoryFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}/offers")
    public ResponseEntity<List<Offer>> findCategoryOffers(@PathVariable("id") String id) {
        List<Offer> offers = categoryService.findOffers(categoryService.findById(Integer.parseInt(id)));
        if (offers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }
}
