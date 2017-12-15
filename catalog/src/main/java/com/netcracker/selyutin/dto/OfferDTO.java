package com.netcracker.selyutin.dto;

import java.util.Objects;

public class OfferDTO {

    private int id;

    private String name;

    private String description;

    private boolean availability;

    private String categoryName;

    private double price;

    private String imageUrl;

    public OfferDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfferDTO)) return false;
        OfferDTO offerDTO = (OfferDTO) o;
        return id == offerDTO.id &&
                availability == offerDTO.availability &&
                Double.compare(offerDTO.price, price) == 0 &&
                Objects.equals(name, offerDTO.name) &&
                Objects.equals(description, offerDTO.description) &&
                Objects.equals(categoryName, offerDTO.categoryName) &&
                Objects.equals(imageUrl, offerDTO.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, availability, categoryName, price, imageUrl);
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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "OfferDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", availability=" + availability +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
