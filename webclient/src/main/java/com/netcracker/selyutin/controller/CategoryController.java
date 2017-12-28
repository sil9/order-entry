package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("category/list");
        List<Category> categories = categoryService.findAll();
        model.addObject("categories", categories);
        return model;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView model = new ModelAndView("category/createForm");
        Category category = new Category();
        model.addObject("category", category);
        return model;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView model = new ModelAndView("category/updateForm");
        Category category = categoryService.findById(id);
        model.addObject("category", category);
        return model;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return new ModelAndView("redirect:/categories/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("category") Category category) {
        if (category.getId() != null) {
            categoryService.update(category);
        } else {
            categoryService.create(category);
        }
        return new ModelAndView("redirect:/categories/list");
    }
}
