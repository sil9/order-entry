package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("tag/list");
        List<Tag> tags = tagService.findAll();
        model.addObject("tags", tags);
        return model;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView model = new ModelAndView("tag/createForm");
        Tag tag = new Tag();
        model.addObject("tag", tag);
        return model;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView model = new ModelAndView("tag/updateForm");
        Tag tag = tagService.findById(id);
        model.addObject("tag", tag);
        return model;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        tagService.delete(id);
        return new ModelAndView("redirect:/tags/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("tag") Tag tag) {
        if (tag.getId() != null) {
            tagService.update(tag);
        } else {
            tagService.create(tag);
        }
        return new ModelAndView("redirect:/tags/list");
    }
}
