package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Tag;

import java.util.List;

public interface TagService {

    Tag findById(int id);

    void create(Tag tag);

    void update(Tag tag);

    void delete(int id);

    List<Tag> findAll();
}
