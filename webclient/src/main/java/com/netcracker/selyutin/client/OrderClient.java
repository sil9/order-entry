package com.netcracker.selyutin.client;

import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class OrderClient {

    private final RestTemplate restTemplate;

    @Value("${client.url.order}")
    private String url;

    @Autowired
    public OrderClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Order createOrder(Order order) {
        return restTemplate.postForObject(url, order, Order.class);
    }

    public void deleteOrder(int id) {
        restTemplate.delete(url + "/{id}", id);
    }

    public void payOrder(int id) {
        restTemplate.put(url + "/{id}/pay", null, id);
    }

    public List<Order> findCustomerOrdersByStatus(String customerMail, Status status) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url + "/search")
                .queryParam("customerMail", customerMail)
                .queryParam("status", status);
        Order[] orders = restTemplate.getForObject(builder.toUriString(), Order[].class);
        return Arrays.asList(orders);
    }

    public void deleteOrderItem(int orderId, int itemId) {
        restTemplate.delete(url + "/{orderId}/orderItems/{itemId}", orderId, itemId);
    }

    public void addOrderItem(int orderId, int offerId) {
        restTemplate.postForObject(url + "/{orderId}/orderItems?offerId={offerId}", null,
                Order.class, orderId, offerId);
    }
}
