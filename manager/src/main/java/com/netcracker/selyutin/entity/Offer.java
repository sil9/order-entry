package com.netcracker.selyutin.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Offer extends IdentifiedEntity {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String fullDescription;

    @NotNull
    private boolean availability;

    private String categoryName;

    @NotNull
    private Double price;

    @NotNull
    private String imageUrl;

    public Offer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        if (!super.equals(o)) return false;
        Offer offer = (Offer) o;
        return availability == offer.availability &&
                Objects.equals(name, offer.name) &&
                Objects.equals(description, offer.description) &&
                Objects.equals(fullDescription, offer.fullDescription) &&
                Objects.equals(categoryName, offer.categoryName) &&
                Objects.equals(price, offer.price) &&
                Objects.equals(imageUrl, offer.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, fullDescription, availability, categoryName, price, imageUrl);
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

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
        return "Offer{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", availability=" + availability +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
