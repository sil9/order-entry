package com.netcracker.selyutin.entity;


import java.util.Objects;

public abstract class IdentifiedEntity {

    private int id;

    public IdentifiedEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifiedEntity IdentifiedEntity = (IdentifiedEntity) o;
        return id == IdentifiedEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
