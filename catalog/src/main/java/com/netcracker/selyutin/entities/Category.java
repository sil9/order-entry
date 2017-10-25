package com.netcracker.selyutin.entities;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Category extends Entity {
    private String name;
    private Set<Offer> offers = new HashSet<Offer>();

    public Category() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(offers, category.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, offers);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", offers=" + offers +
                '}';
    }
}
