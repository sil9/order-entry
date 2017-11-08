package com.netcracker.selyutin.entity;


import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.Objects;

@javax.persistence.Entity
public class Price extends Entity {

    private long value;

    @OneToOne(mappedBy = "price", fetch = FetchType.LAZY)
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

    public long getValue() {
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
