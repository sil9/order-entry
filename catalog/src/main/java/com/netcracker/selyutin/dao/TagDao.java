package com.netcracker.selyutin.dao;


import com.netcracker.selyutin.entity.Tag;

import java.util.List;

public interface TagDao extends BaseDao<Tag> {

    List<Tag> findByName(String name);

    List<Tag> findByOffer(int id);

}
