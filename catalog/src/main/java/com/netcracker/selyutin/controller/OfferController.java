package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.*;
import com.netcracker.selyutin.service.CategoryService;
import com.netcracker.selyutin.service.OfferService;
import com.netcracker.selyutin.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/offers")
@Api(description = "Operations with offers")
public class OfferController {

    private final OfferService offerService;
    private final TagService tagService;
    private final CategoryService categoryService;

    @Autowired
    OfferController(OfferService offerService, TagService tagService, CategoryService categoryService) {
        this.offerService = offerService;
        this.tagService = tagService;
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Create offer")
    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer createdOffer = offerService.create(offer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update offer")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable Integer id, @RequestBody Offer offer) {
        if (id != offer.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Offer offerFromDatabase = offerService.findById(id);
        if (offerFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        offerService.update(offer);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @ApiOperation(value = "Remove offer by specific id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Integer id) {
        Offer offerFromDatabase = offerService.findById(id);
        if (offerFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        offerService.delete(offerService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Change offer's availability")
    @PutMapping(value = "/{id}/availability")
    public ResponseEntity<Offer> changeAvailability(@PathVariable Integer id, @RequestParam Boolean availability) {
        Offer offer = offerService.findById(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        offer.setAvailability(availability);
        offerService.update(offer);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @ApiOperation(value = "Get offer by specific id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Offer> findById(@PathVariable Integer id) {
        Offer offer = offerService.findById(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @ApiOperation(value = "Get offers with specific parameters")
    @PostMapping(value = "/search")
    public ResponseEntity<List<Offer>> findAll(@RequestBody Map<String, Object> filters) {
         List<Offer> offers = offerService.findAllWithFilter(filters);
         return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @ApiOperation(value = "Get offers by specific tag's id")
    @GetMapping(value = "/tags/{id}")
    public ResponseEntity<List<Offer>> findByTag(@RequestParam Integer id) {
        List<Offer> offers = offerService.findByTag(id);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @ApiOperation(value = "Get available offers")
    @GetMapping(value = "/?availability=true")
    public ResponseEntity<List<Offer>> findAvailable() {
        List<Offer> offers = offerService.findAvailable();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @ApiOperation(value = "Add price to offer")
    @PostMapping(value = "/{id}/price")
    public ResponseEntity<Offer> addPrice(@PathVariable Integer id, @RequestBody Price price) {
        Offer offer = offerService.findById(id);
        if (offer.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        offer.setPrice(price);
        price.setOffer(offer);
        Offer updatedOffer = offerService.update(offer);
        return new ResponseEntity<>(updatedOffer, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Change offer's price")
    @PutMapping(value = "/{id}/price")
    public ResponseEntity<Offer> updatePrice(@PathVariable Integer id, @RequestParam Double value) {
        Offer offer = offerService.findById(id);
        if (offer.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        offer.getPrice().setValue(value);
        Offer updatedOffer = offerService.update(offer);
        return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
    }

    @ApiOperation(value = "Get offers by range of value")
    @GetMapping(value = "/price")
    public ResponseEntity<List<Offer>> findByPrice(@RequestParam("val1") Double firstValue,
                                                   @RequestParam("val2") Double secondValue) {
        List<Offer> offers = offerService.findByPrice(firstValue, secondValue);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @ApiOperation(value = "Get offers by range of value")
    @PostMapping(value = "/{id}/tag")
    public ResponseEntity<Offer> addTag(@PathVariable Integer id ,@RequestBody Tag tag) {
        Offer offer = offerService.findById(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tagService.create(tag);
        offer.getTags().add(tag);
        offerService.update(offer);
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove tag from offer")
    @DeleteMapping(value = "/{id}/tags/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable Integer id, @PathVariable Integer tagId) {
        Offer offer = offerService.findById(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Tag tag = tagService.findById(tagId);
        if (tag == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        offer.getTags().remove(tag);
        tag.getOffers().remove(offer);
        offerService.update(offer);
        tagService.update(tag);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Add offer to category")
    @PostMapping(value = "/{id}/category")
    public ResponseEntity<Offer> addOfferToCategory(@PathVariable Integer id, @RequestBody Category category) {
        Offer offer = offerService.findById(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        offer.setCategory(category);
        category.getOffers().add(offer);
        categoryService.update(category);
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Remove offer from category")
    @DeleteMapping(value = "/{id}/category/{categoryId}")
    public ResponseEntity<Void> deleteOfferFromCategory(@PathVariable Integer id, @PathVariable Integer categoryId) {
        Offer offer = offerService.findById(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Category category = categoryService.findById(categoryId);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        offer.setCategory(null);
        offerService.update(offer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
