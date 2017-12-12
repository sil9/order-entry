package com.netcracker.selyutin.client;

import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.exception.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CatalogClient {

    @Value("${client.url.catalog}")
    private String url;

    private final RestTemplate restTemplate;

    @Autowired
    public CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Offer> findAllOffer(Map<String, Object> params) {
        Offer[] offers = restTemplate.postForObject(url + "/offers/search", params, Offer[].class);
        return Arrays.asList(offers);
    }

    public Offer findOfferById(Integer offerId) throws EntityNotFoundException {
        try {
            ResponseEntity<Offer> response = restTemplate.getForEntity(url + "/offers/{id}", Offer.class, offerId);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                throw new EntityNotFoundException(Offer.class, offerId);
            } else {
                throw new ResponseException(e.getStatusCode());
            }
        }
    }
}
