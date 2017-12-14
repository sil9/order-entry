package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categories")
@Api(description = "Operations with categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Create category")
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.create(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get category by specific id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @ApiOperation(value = "Get offers by specific name")
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<Category>> findByName(@PathVariable("name") String name) {
        List<Category> categories = categoryService.findByName(name);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all categories")
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @ApiOperation(value = "Update category")
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

    @ApiOperation(value = "Remove category by specific id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id) {
        Category categoryFromDatabase = categoryService.findById(id);
        if (categoryFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(categoryService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get all offers by specific category")
    @GetMapping(value = "/{id}/offers")
    public ResponseEntity<List<Offer>> findCategoryOffers(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Offer> offers = categoryService.findOffers(category);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }
}
