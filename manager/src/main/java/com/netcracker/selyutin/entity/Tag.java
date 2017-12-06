package com.netcracker.selyutin.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag extends IdentifiedEntity {

    private String sign;

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

    @Override
    public String toString() {
        return "Tag{" + "sign='" + sign + '\'' +
                '}';
    }
}
