package com.netcracker.selyutin.service;

import com.netcracker.selyutin.entity.Tag;

import java.util.List;

public interface TagService extends BaseService<Tag> {

    List<Tag> findByName(String name);

    List<Tag> findByOffer(int id);

}
