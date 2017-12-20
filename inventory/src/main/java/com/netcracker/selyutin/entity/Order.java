package com.netcracker.selyutin.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "t_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    private String description;

    private String customerEmail;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate activeDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Transient
    private double totalPrice;

    @Transient
    private int itemsCount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.totalPrice, totalPrice) == 0 &&
                itemsCount == order.itemsCount &&
                Objects.equals(name, order.name) &&
                Objects.equals(description, order.description) &&
                Objects.equals(customerEmail, order.customerEmail) &&
                Objects.equals(activeDate, order.activeDate) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, customerEmail, activeDate, status, totalPrice, itemsCount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDate getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDate creationDate) {
        this.activeDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", creationDate=" + activeDate +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", itemsCount=" + itemsCount +
                '}';
    }
}
