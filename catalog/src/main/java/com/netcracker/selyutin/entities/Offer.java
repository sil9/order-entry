package com.netcracker.selyutin.entities;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Offer extends Entity {
    private String name;
    private String description;
    private boolean availability;
    private Category category;
    private Price price;
    private Set<Tag> tags = new HashSet<Tag>();

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
                Objects.equals(category, offer.category) &&
                Objects.equals(price, offer.price) &&
                Objects.equals(tags, offer.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, availability, category, price, tags);
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", availability=" + availability +
                ", category=" + category +
                ", price=" + price +
                ", tags=" + tags +
                '}';
    }
}
