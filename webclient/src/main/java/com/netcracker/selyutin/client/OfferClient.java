package com.netcracker.selyutin.client;

import com.netcracker.selyutin.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class OfferClient {

    private final RestTemplate restTemplate;

    @Value("${client.url.offer}")
    private String url;

    @Autowired
    public OfferClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Offer create(Offer offer) {
        return restTemplate.postForObject(url, offer, Offer.class);
    }

    public void update(Offer offer) {
        restTemplate.put(url + "/{id}", offer, offer.getId());
    }

    public Offer addOfferToCategory(int offerId, int categoryId) {
        return restTemplate.postForObject(url + "/{id}/category/{categoryId}", null, Offer.class, offerId, categoryId);
    }

    public void delete(int id) {
        restTemplate.delete(url + "/{id}", id);
    }

    public Offer findById(int id) {
        return restTemplate.getForObject(url + "/{id}", Offer.class, id);
    }

    public List<Offer> findWithFilter(Map<String, Object> params) {
        Offer[] offers = restTemplate.postForObject(url + "/search", params, Offer[].class);
        return Arrays.asList(offers);
    }

}
