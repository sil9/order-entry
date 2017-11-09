package com.netcracker.selyutin.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class IdentifiedEntity implements Cloneable, Serializable {

    @Id
    @GeneratedValue
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
