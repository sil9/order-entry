package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.client.CategoryClient;
import com.netcracker.selyutin.client.OfferClient;
import com.netcracker.selyutin.client.TagClient;
import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private OfferClient offerClient;

    private CategoryClient categoryClient;

    private TagClient tagClient;

    @Autowired
    public OfferServiceImpl(OfferClient offerClient, CategoryClient categoryClient, TagClient tagClient) {
        this.categoryClient = categoryClient;
        this.offerClient = offerClient;
        this.tagClient = tagClient;
    }

    @Override
    public Offer findById(int id) {
        return offerClient.findById(id);
    }

    @Override
    public void create(Offer offer) {
        Offer createdOffer = offerClient.create(offer);
        Category category = categoryClient.findByName(offer.getCategoryName());
        offerClient.addOfferToCategory(createdOffer.getId(), category.getId());
    }

    @Override
    public void update(Offer offer) {
        offerClient.update(offer);
        Category category = categoryClient.findByName(offer.getCategoryName());
        offerClient.addOfferToCategory(offer.getId(), category.getId());
    }

    @Override
    public void delete(int id) {
        offerClient.delete(id);
    }

    @Override
    public List<Offer> findWithFilter(Map<String, Object> params) {
        return offerClient.findWithFilter(params);
    }

    @Override
    public List<Offer> findRelatedOffers(int offerId) {
        List<Tag> tags = tagClient.findTagsByOffer(offerId);
        Set<Offer> offers = new HashSet<>();
        for (Tag tag : tags) {
            Map<String, Object> filter = new HashMap<>();
            filter.put("tagName", Arrays.asList(tag.getSign()));
            filter.put("availability", true);
            List<Offer> filterOffers = offerClient.findWithFilter(filter);
            offers.addAll(filterOffers);
        }
        return offers.stream().limit(6).collect(Collectors.toList());
    }
}
