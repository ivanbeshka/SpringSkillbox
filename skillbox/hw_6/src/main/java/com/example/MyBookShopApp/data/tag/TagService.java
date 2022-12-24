package com.example.MyBookShopApp.data.tag;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagEntityRepository tagEntityRepository;

    public TagService(TagEntityRepository tagEntityRepository) {
        this.tagEntityRepository = tagEntityRepository;
    }

    public List<TagEntity> getAllTags() {
        return tagEntityRepository.findAll();
    }

    public TagEntity getTagById(Integer tagId) {
        return tagEntityRepository.findById(tagId).get();
    }
}
