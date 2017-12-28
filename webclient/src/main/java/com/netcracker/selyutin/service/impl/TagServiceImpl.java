package com.netcracker.selyutin.service.impl;

import com.netcracker.selyutin.client.TagClient;
import com.netcracker.selyutin.entity.Tag;
import com.netcracker.selyutin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagClient tagClient;

    @Autowired
    public TagServiceImpl(TagClient tagClient) {
        this.tagClient = tagClient;
    }

    @Override
    public Tag findById(int id) {
        return tagClient.findById(id);
    }

    @Override
    public void create(Tag tag) {
        tagClient.create(tag);
    }

    @Override
    public void update(Tag tag) {
        tagClient.update(tag);
    }

    @Override
    public void delete(int id) {
        tagClient.delete(id);
    }

    @Override
    public List<Tag> findAll() {
        return tagClient.findAll();
    }
}
