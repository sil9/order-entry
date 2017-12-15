package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.dto.CategoryDTO;
import com.netcracker.selyutin.dto.OfferDTO;
import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/categories")
@Api(description = "Operations with categories")
public class CategoryController {

    private final CategoryService categoryService;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Create category")
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO category) {
        Category createdCategory = categoryService.create(modelMapper.map(category, Category.class));
        return new ResponseEntity<>(modelMapper.map(createdCategory, CategoryDTO.class), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get category by specific id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Integer id) throws EntityNotFoundException {
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(modelMapper.map(category, CategoryDTO.class), HttpStatus.OK);
    }

    @ApiOperation(value = "Get categories by specific name")
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name") String name) {
        List<Category> result = categoryService.findByName(name);
        List<CategoryDTO> categories = result.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all categories")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> result = categoryService.findAll();
        List<CategoryDTO> categories = result.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @ApiOperation(value = "Update category")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryDTO category) throws EntityNotFoundException {
        if (id != category.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        categoryService.findById(id);
        categoryService.update(modelMapper.map(category, Category.class));
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @ApiOperation(value = "Remove category by specific id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id) throws EntityNotFoundException {
        categoryService.delete(categoryService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Get all offers by specific category")
    @GetMapping(value = "/{id}/offers")
    public ResponseEntity<List<OfferDTO>> findCategoryOffers(@PathVariable("id") Integer id) throws EntityNotFoundException {
        Category category = categoryService.findById(id);
        List<Offer> result = categoryService.findOffers(category);
        List<OfferDTO> offers = result.stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }
}
