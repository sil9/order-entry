package com.netcracker.selyutin.entities;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tag extends Entity {
    private String sign;
    private Set<Offer> offers = new HashSet<Offer>();

    public Tag() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        if (!super.equals(o)) return false;
        Tag tag = (Tag) o;
        return Objects.equals(sign, tag.sign) &&
                Objects.equals(offers, tag.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sign, offers);
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id='" + getId() +
                ", sign='" + sign + '\'' +
                ", offers=" + offers +
                '}';
    }
}
