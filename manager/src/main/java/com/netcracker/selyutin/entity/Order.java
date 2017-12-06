package com.netcracker.selyutin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order extends IdentifiedEntity {

    private String name;

    private String description;

    private String customerEmail;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate creationDate;

    private String paymentSign;

    private boolean paymentStatus;   //true - paid, false unpaid

    private double totalPrice;

    private int itemsCount;

    private List<OrderItem> orderItems;

    public Order() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return paymentStatus == order.paymentStatus &&
                Double.compare(order.totalPrice, totalPrice) == 0 &&
                itemsCount == order.itemsCount &&
                Objects.equals(name, order.name) &&
                Objects.equals(description, order.description) &&
                Objects.equals(customerEmail, order.customerEmail) &&
                Objects.equals(creationDate, order.creationDate) &&
                Objects.equals(paymentSign, order.paymentSign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, customerEmail, creationDate, paymentSign, paymentStatus, totalPrice, itemsCount);
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getPaymentSign() {
        return paymentSign;
    }

    public void setPaymentSign(String paymentSign) {
        this.paymentSign = paymentSign;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", creationDate=" + creationDate +
                ", paymentSign='" + paymentSign + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", totalPrice=" + totalPrice +
                ", itemsCount=" + itemsCount +
                ", orderItems=" + orderItems +
                '}';
    }
}
