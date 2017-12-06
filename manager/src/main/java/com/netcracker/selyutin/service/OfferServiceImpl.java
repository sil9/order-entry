package com.netcracker.selyutin.service;

import com.netcracker.selyutin.client.CatalogClient;
import com.netcracker.selyutin.constant.ExceptionMessage;
import com.netcracker.selyutin.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OfferServiceImpl implements OfferService {

    private final CatalogClient catalogClient;

    @Autowired
    OfferServiceImpl(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @Override
    public List<Offer> findWithFilter(String categoryName, Double minPrice, Double maxPrice, List<String> tagsNames) {
        if (minPrice < 0 || maxPrice < 0 || maxPrice < minPrice) {
            throw new IllegalArgumentException(ExceptionMessage.INCORRECT_DATA);
        }
        List<Offer> offers = catalogClient.findAllOffer();
        if (minPrice != 0) {
            offers.removeIf(offer -> offer.getPrice().getValue() < minPrice);
        }
        if (maxPrice != 999999999999.9) {
            offers.removeIf(offer -> offer.getPrice().getValue() > maxPrice);
        }
        if (!categoryName.equals("emptyCategory")) {
            offers.removeIf(offer -> !offer.getCategory().getName().equals(categoryName));
        }
        if (!tagsNames.get(0).equals("emptyTag")) {
            for (final String tagName : tagsNames) {
                offers.removeIf(offer -> offer.getTags().removeIf(tag -> !tag.getSign().equals(tagName)));
            }
        }
        return offers;
    }
}
