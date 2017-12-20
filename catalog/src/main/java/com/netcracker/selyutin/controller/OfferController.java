package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.dto.OfferDTO;
import com.netcracker.selyutin.dto.TagDTO;
import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Price;
import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.service.CategoryService;
import com.netcracker.selyutin.service.OfferService;
import com.netcracker.selyutin.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/offers")
@Api(description = "Operations with offers")
public class OfferController {

    private final OfferService offerService;
    private final TagService tagService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService, TagService tagService, CategoryService categoryService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.tagService = tagService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "Create offer")
    @PostMapping
    public ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offer) {
        Offer createdOffer = offerService.create(modelMapper.map(offer, Offer.class));
        return new ResponseEntity<>(modelMapper.map(createdOffer, OfferDTO.class), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update offer")
    @PutMapping(value = "/{id}")
    public ResponseEntity<OfferDTO> updateOffer(@PathVariable Integer id, @RequestBody OfferDTO offer) throws EntityNotFoundException {
        if (id != offer.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        offerService.update(modelMapper.map(offer, Offer.class));
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @ApiOperation(value = "Remove offer by specific id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Integer id) throws EntityNotFoundException {
        offerService.delete(offerService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Change offer's availability")
    @PutMapping(value = "/{id}/availability")
    public ResponseEntity<OfferDTO> changeAvailability(@PathVariable Integer id, @RequestParam Boolean availability) throws EntityNotFoundException {
        Offer offer = offerService.findById(id);
        offer.setAvailability(availability);
        offerService.update(offer);
        return new ResponseEntity<>(modelMapper.map(offer, OfferDTO.class), HttpStatus.OK);
    }

    @ApiOperation(value = "Get offer by specific id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OfferDTO> findById(@PathVariable Integer id) throws EntityNotFoundException {
        Offer offer = offerService.findById(id);
        return new ResponseEntity<>(modelMapper.map(offer, OfferDTO.class), HttpStatus.OK);
    }

    @ApiOperation(value = "Get offers with specific parameters")
    @PostMapping(value = "/search")
    public ResponseEntity<List<OfferDTO>> findAll(@RequestBody Map<String, Object> filters) {
        List<Offer> result = offerService.findAllWithFilter(filters);
        List<OfferDTO> offers = result.stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @ApiOperation(value = "Get offers by specific tag's id")
    @GetMapping(value = "/tags/{id}")
    public ResponseEntity<List<OfferDTO>> findByTag(@RequestParam Integer id) {
        List<Offer> result = offerService.findByTag(id);
        List<OfferDTO> offers = result.stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @ApiOperation(value = "Get available offers")
    @GetMapping(value = "/getAvailable")
    public ResponseEntity<List<OfferDTO>> findAvailable() {
        List<Offer> result = offerService.findAvailable();
        List<OfferDTO> offers = result.stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @ApiOperation(value = "Add price to offer")
    @PostMapping(value = "/{id}/price")
    public ResponseEntity<OfferDTO> addPrice(@PathVariable Integer id, @RequestParam Double price) throws EntityNotFoundException {
        Offer offer = offerService.findById(id);
        Price price1 = new Price();
        price1.setValue(price);
        offer.setPrice(price1);
        //price.setOffer(offer);
        Offer updatedOffer = offerService.update(offer);
        return new ResponseEntity<>(modelMapper.map(updatedOffer, OfferDTO.class), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Change offer's price")
    @PutMapping(value = "/{id}/price")
    public ResponseEntity<OfferDTO> updatePrice(@PathVariable Integer id, @RequestParam Double value) throws EntityNotFoundException {
        Offer offer = offerService.findById(id);
        offer.getPrice().setValue(value);
        Offer updatedOffer = offerService.update(offer);
        return new ResponseEntity<>(modelMapper.map(updatedOffer, OfferDTO.class), HttpStatus.OK);
    }

    @ApiOperation(value = "Get offers by range of value")
    @GetMapping(value = "/price")
    public ResponseEntity<List<OfferDTO>> findByPrice(@RequestParam("val1") Double firstValue,
                                                      @RequestParam("val2") Double secondValue) {
        List<Offer> result = offerService.findByPrice(firstValue, secondValue);
        List<OfferDTO> offers = result.stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @ApiOperation(value = "Add tag to offer")
    @PostMapping(value = "/{id}/tag")
    public ResponseEntity<OfferDTO> addTag(@PathVariable Integer id, @RequestBody TagDTO tag) throws EntityNotFoundException {
        Offer offer = offerService.findById(id);
        Tag entityTag = modelMapper.map(tag, Tag.class);
        tagService.create(entityTag);
        offer.getTags().add(entityTag);
        offerService.update(offer);
        return new ResponseEntity<>(modelMapper.map(offer, OfferDTO.class), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove tag from offer")
    @DeleteMapping(value = "/{id}/tags/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable Integer id, @PathVariable Integer tagId) throws EntityNotFoundException {
        Offer offer = offerService.findById(id);
        Tag tag = tagService.findById(tagId);
        offer.getTags().remove(tag);
        tag.getOffers().remove(offer);
        offerService.update(offer);
        tagService.update(tag);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Add offer to category")
    @PostMapping(value = "/{id}/category")
    public ResponseEntity<OfferDTO> addOfferToCategory(@PathVariable Integer id, @RequestParam Integer categoryId) throws EntityNotFoundException {
        Offer offer = offerService.findById(id);
        Category entityCategory = categoryService.findById(categoryId);
        offer.setCategory(entityCategory);
        entityCategory.getOffers().add(offer);
        categoryService.update(entityCategory);
        return new ResponseEntity<>(modelMapper.map(offer, OfferDTO.class), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove offer from category")
    @DeleteMapping(value = "/{id}/category")
    public ResponseEntity<Void> deleteOfferFromCategory(@PathVariable Integer id) throws EntityNotFoundException {
        Offer offer = offerService.findById(id);
        offer.setCategory(null);
        offerService.update(offer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
