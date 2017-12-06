package com.netcracker.selyutin.client;

import com.netcracker.selyutin.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CatalogClient {

    @Value("${client.url.catalog}")
    private String url;

    private final RestTemplate restTemplate;

    @Autowired
    CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Offer> findAllOffer() {
        Offer[] offers = restTemplate.getForObject(url + "/offers", Offer[].class);
        return Arrays.stream(offers).collect(Collectors.toList());
    }
}
