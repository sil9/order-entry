package com.netcracker.selyutin.entity;


import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@javax.persistence.Entity
public class Tag extends Entity {

    private String sign;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<Offer> offers = new HashSet<>();

    public Tag() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        if (!super.equals(o)) return false;
        Tag tag = (Tag) o;
        return Objects.equals(sign, tag.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sign);
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
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("sign='").append(sign).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
