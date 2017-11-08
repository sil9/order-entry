package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.dao.TagDao;
import com.netcracker.selyutin.entities.Tag;

public class TagDaoImpl extends GenericDao<Tag> implements TagDao {

    private TagDaoImpl() {
        super(Tag.class);
    }

    private static class Holder {
        private final static TagDaoImpl INSTANCE = new TagDaoImpl();
    }

    public static TagDaoImpl getInstance() {
        return Holder.INSTANCE;
    }
}
