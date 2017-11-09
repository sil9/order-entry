package com.netcracker.selyutin.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "T_Order")
public class Order {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private long totalPrice;
    private int itemsCount;
    private String customerEmail;
    private LocalDate date;
    private String paymentSign;

    @Transient
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                totalPrice == order.totalPrice &&
                itemsCount == order.itemsCount &&
                Objects.equals(name, order.name) &&
                Objects.equals(description, order.description) &&
                Objects.equals(customerEmail, order.customerEmail) &&
                Objects.equals(date, order.date) &&
                Objects.equals(paymentSign, order.paymentSign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, totalPrice, itemsCount, customerEmail, date, paymentSign);
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

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPaymentSign() {
        return paymentSign;
    }

    public void setPaymentSign(String paymentSign) {
        this.paymentSign = paymentSign;
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
                ", totalPrice=" + totalPrice +
                ", itemsCount=" + itemsCount +
                ", customerEmail='" + customerEmail + '\'' +
                ", date=" + date +
                ", paymentSign='" + paymentSign + '\'' +
                '}';
    }
}
