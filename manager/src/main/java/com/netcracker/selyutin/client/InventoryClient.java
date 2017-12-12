package com.netcracker.selyutin.client;

import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.OrderItem;
import com.netcracker.selyutin.exception.ResponseException;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InventoryClient {

    @Value("${client.url.inventory}")
    private String url;

    private final RestTemplate restTemplate;

    @Autowired
    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Order findById(int id) throws EntityNotFoundException {
        try {
            ResponseEntity<Order> response = restTemplate.getForEntity(url + "/orders/{id}", Order.class, id);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                throw new EntityNotFoundException(Order.class, id);
            } else {
                throw new ResponseException(e.getStatusCode());
            }
        }
    }

    public Order addOrderItem(int id, OrderItem orderItem) {
        ResponseEntity<Order> response = restTemplate.postForEntity(url + "/orders/{id}/orderItems", orderItem, Order.class, id);
        return response.getBody();
    }

    public void deleteOrderItem(int id, int orderId) {
        restTemplate.delete(url + "/orders/{id}/orderItems/{orderId}", id, orderId);
    }

    public List<Order> findByCustomer(String customerMail) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url + "/orders/customerEmail")
                .queryParam("customerEmail", customerMail);
        Order[] orders = restTemplate.getForObject(builder.toUriString(), Order[].class);
        return Arrays.asList(orders);
    }

    public void updateOrder(Order order) {
        restTemplate.put(url + "/orders", order);
    }

    public List<Order> findAll() {
        Order[] orders = restTemplate.getForObject(url + "/orders", Order[].class);
        return Arrays.asList(orders);
    }

    public Order createOrder(Order order) {
        ResponseEntity<Order> response = restTemplate.postForEntity(url + "/orders", order, Order.class);
        return response.getBody();
    }
}
