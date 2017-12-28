package com.netcracker.selyutin.client;

import com.netcracker.selyutin.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryClient {

    private final RestTemplate restTemplate;

    @Value("${client.url.category}")
    private String url;

    @Autowired
    public CategoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Category findByName(String name) {
        return restTemplate.getForObject(url + "/search?name={name}", Category[].class, name)[0];
    }

    public Category findById(int id) {
        return restTemplate.getForObject(url + "/{id}", Category.class, id);
    }

    public Category create(Category category) {
        return restTemplate.postForObject(url, category, Category.class);
    }

    public List<Category> findAll() {
        return Arrays.asList(restTemplate.getForObject(url, Category[].class));
    }

    public void update(Category category) {
        restTemplate.put(url + "/{id}", category, category.getId());
    }

    public void delete(int id) {
        restTemplate.delete(url + "/{id}", id);
    }

}
