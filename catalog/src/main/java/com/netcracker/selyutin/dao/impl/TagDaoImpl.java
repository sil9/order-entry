package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.constant.DatabaseQuery;
import com.netcracker.selyutin.dao.TagDao;
import com.netcracker.selyutin.entity.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TagDaoImpl extends GenericDao<Tag> implements TagDao {

    public TagDaoImpl() {
        super(Tag.class);
    }

    @Override
    public List<Tag> findByName(String name) {
        TypedQuery<Tag> query = entityManager.createQuery(DatabaseQuery.FIND_TAGS_BY_NAME, Tag.class);
        query.setParameter("sign", name);
        return query.getResultList();
    }

    @Override
    public List<Tag> findByOffer(int id) {
        TypedQuery<Tag> query = entityManager.createQuery(DatabaseQuery.FIND_TAGS_BY_OFFER, Tag.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
