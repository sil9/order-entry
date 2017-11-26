package com.netcracker.selyutin.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Price extends IdentifiedEntity {

    private double value;

    @OneToOne(mappedBy = "price", fetch = FetchType.LAZY)
    @JsonIgnore
    private Offer offer;

    public Price() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        if (!super.equals(o)) return false;
        Price price = (Price) o;
        return value == price.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + getId() +
                ", value=" + value +
                '}';
    }
}
