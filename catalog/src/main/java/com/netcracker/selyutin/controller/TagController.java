package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag createdTag = tagService.create(tag);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tag> findById(@PathVariable Integer id) {
        Tag tag = tagService.findById(id);
        if (tag == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<Tag>> findByName(@PathVariable String name) {
        List<Tag> tags = tagService.findByName(name);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Tag>> findAll() {
        List<Tag> tags = tagService.findAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping(value = "/offers/{id}")
    public ResponseEntity<List<Tag>> findTagOffers(@PathVariable Integer id) {
        List<Tag> offers = tagService.findByOffer(id);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Tag> updateCategory(@PathVariable("id") Integer id, @RequestBody Tag tag) {
        if (id != tag.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Tag tagFromDatabase = tagService.findById(id);
        if (tagFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tagService.update(tag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id) {
        Tag tagFromDatabase = tagService.findById(id);
        if (tagFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tagService.delete(tagService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
