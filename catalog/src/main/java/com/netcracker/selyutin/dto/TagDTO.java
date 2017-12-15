package com.netcracker.selyutin.dto;

import java.util.Objects;

public class TagDTO {

    private int id;

    private String sign;

    public TagDTO() {
    }

    public TagDTO(int id, String sign) {
        this.id = id;
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagDTO)) return false;
        TagDTO tagDTO = (TagDTO) o;
        return id == tagDTO.id &&
                Objects.equals(sign, tagDTO.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sign);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "id=" + id +
                ", sign='" + sign + '\'' +
                '}';
    }
}
