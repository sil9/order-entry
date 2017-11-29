package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.dao.TagDao;
import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl extends GenericService<Tag> implements TagService {

    private final TagDao tagDao;

    @Autowired
    TagServiceImpl(TagDao tagDao) {
        super(tagDao);
        this.tagDao = tagDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findByOffer(int id) {
        return tagDao.findByOffer(id);
    }
}
