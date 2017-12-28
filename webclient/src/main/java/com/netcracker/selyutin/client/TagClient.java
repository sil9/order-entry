package com.netcracker.selyutin.client;

import com.netcracker.selyutin.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class TagClient {
    private final RestTemplate restTemplate;

    @Value("${client.url.tag}")
    private String url;

    @Autowired
    public TagClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Tag findById(int id) {
        return restTemplate.getForObject(url + "/{id}", Tag.class, id);
    }

    public Tag create(Tag tag) {
        return restTemplate.postForObject(url, tag, Tag.class);
    }

    public List<Tag> findAll() {
        return Arrays.asList(restTemplate.getForObject(url, Tag[].class));
    }

    public void update(Tag tag) {
        restTemplate.put(url + "/{id}", tag, tag.getId());
    }

    public void delete(int id) {
        restTemplate.delete(url + "/{id}", id);
    }

    public List<Tag> findTagsByOffer(int offerId) {
        return Arrays.asList(restTemplate.getForObject(url + "/offers/{id}", Tag[].class, offerId));
    }
}
