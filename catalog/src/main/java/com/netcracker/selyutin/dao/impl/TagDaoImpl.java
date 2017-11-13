package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.TagDao;
import com.netcracker.selyutin.entity.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl extends GenericDao<Tag> implements TagDao {

    TagDaoImpl() {
        super(Tag.class);
    }

}
