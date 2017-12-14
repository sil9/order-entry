package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.service.OfferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/offers")
@Api(description = "Operations with offers")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @ApiOperation(value = "Get offers with specific parameters")
    @PostMapping
    public ResponseEntity<List<Offer>> findWithFilter(@RequestBody Map<String, Object> params) {
        List<Offer> offers = offerService.findWithFilter(params);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

}
