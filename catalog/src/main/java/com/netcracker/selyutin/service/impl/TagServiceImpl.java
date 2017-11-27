package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.dao.TagDao;
import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends GenericService<Tag> implements TagService {

    private final TagDao tagDao;

    @Autowired
    TagServiceImpl(TagDao tagDao) {
        super(tagDao);
        this.tagDao = tagDao;
    }
}
