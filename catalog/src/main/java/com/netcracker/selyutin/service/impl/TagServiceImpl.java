package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.constant.LoggerConstant;
import com.netcracker.selyutin.dao.TagDao;
import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.service.TagService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl extends GenericService<Tag> implements TagService {

    private static final Logger LOGGER = LogManager.getLogger(TagServiceImpl.class);

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        super(tagDao);
        this.tagDao = tagDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findByName(String name) {
        LOGGER.info("Find tags by name: " + name);
        List<Tag> tags = tagDao.findByName(name);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(tags);
        return tags;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findByOffer(int id) {
        LOGGER.info("Find tags by offerId: " + id);
        List<Tag> tags = tagDao.findByOffer(id);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
        LOGGER.info(tags);
        return tags;
    }

    @Override
    @Transactional
    public Tag update(Tag entity) throws EntityNotFoundException {
        Tag tag = tagDao.getById(entity.getId());
        entity.setOffers(tag.getOffers());
        return super.update(entity);
    }
}
