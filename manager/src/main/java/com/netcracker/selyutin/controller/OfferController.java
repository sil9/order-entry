package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/offers")
public class OfferController {

    private final OfferService offerService;

    @Autowired

    OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public ResponseEntity<List<Offer>> findWithFilter(@RequestParam(defaultValue = "emptyCategory") String categoryName,
                                                      @RequestParam(defaultValue = "0") Double minPrice,
                                                      @RequestParam(defaultValue = "999999999999.9") Double maxPrice,
                                                      @RequestParam(value = "tagName", defaultValue = "emptyTag") List<String> tagsNames) {
        List<Offer> offers = offerService.findWithFilter(categoryName, minPrice, maxPrice, tagsNames);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

}
