package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Price;
import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.service.CategoryService;
import com.netcracker.selyutin.service.OfferService;
import com.netcracker.selyutin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/offers")
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

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer createdOffer = offerService.create(offer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Integer id) {
        Offer offerFromDatabase = offerService.findById(id);
        if (offerFromDatabase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        offerService.delete(offerService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Offer> findById(@PathVariable Integer id) {
        Offer offer = offerService.findById(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Offer>> findAll() {
         List<Offer> offers = offerService.findAll();
         return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping(value = "/tags/{id}")
    public ResponseEntity<List<Offer>> findByTag(@RequestParam Integer id) {
        List<Offer> offers = offerService.findByTag(id);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping(value = "/?availability=true")
    public ResponseEntity<List<Offer>> findAvailable() {
        List<Offer> offers = offerService.findAvailable();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

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

    @GetMapping(value = "/price")
    public ResponseEntity<List<Offer>> findByPrice(@RequestParam("val1") Double firstValue,
                                                   @RequestParam("val2") Double secondValue) {
        List<Offer> offers = offerService.findByPrice(firstValue, secondValue);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

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
