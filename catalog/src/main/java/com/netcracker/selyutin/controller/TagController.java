package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.dto.TagDTO;
import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.service.TagService;
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
@RequestMapping(value = "/api/v1/tags")
@Api(description = "Operations with tags")
public class TagController {

    private final TagService tagService;
    private final ModelMapper modelMapper;

    @Autowired
    public TagController(TagService tagService, ModelMapper modelMapper) {
        this.tagService = tagService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Create tag")
    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tag) {
        Tag createdTag = tagService.create(modelMapper.map(tag, Tag.class));
        return new ResponseEntity<>(modelMapper.map(createdTag, TagDTO.class), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get tag by specific id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TagDTO> findById(@PathVariable Integer id) throws EntityNotFoundException {
        Tag tag = tagService.findById(id);
        return new ResponseEntity<>(modelMapper.map(tag, TagDTO.class), HttpStatus.OK);
    }

    @ApiOperation(value = "Get tags by specific name")
    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<TagDTO>> findByName(@PathVariable String name) {
        List<Tag> result = tagService.findByName(name);
        List<TagDTO> tags = result.stream()
                .map(tag -> modelMapper.map(tag, TagDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all tags")
    @GetMapping
    public ResponseEntity<List<TagDTO>> findAll() {
        List<Tag> result = tagService.findAll();
        List<TagDTO> tags = result.stream()
                .map(tag -> modelMapper.map(tag, TagDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @ApiOperation(value = "Get tags by specific offer")
    @GetMapping(value = "/offers/{id}")
    public ResponseEntity<List<TagDTO>> findTagsByOffer(@PathVariable Integer id) {
        List<Tag> result = tagService.findByOffer(id);
        List<TagDTO> tags = result.stream()
                .map(tag -> modelMapper.map(tag, TagDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @ApiOperation(value = "Update tag")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable("id") Integer id, @RequestBody TagDTO tag) throws EntityNotFoundException {
        if (id != tag.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        tagService.findById(id);
        tagService.update(modelMapper.map(tag, Tag.class));
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @ApiOperation(value = "Remove tag by specific id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable("id") Integer id) throws EntityNotFoundException {
        tagService.delete(tagService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
