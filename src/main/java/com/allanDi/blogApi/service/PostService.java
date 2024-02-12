package com.allanDi.blogApi.service;

import com.allanDi.blogApi.model.Post;
import com.allanDi.blogApi.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PostService {

    private final Repository repository;

    @Autowired
    PostService(Repository repository) {
        this.repository = repository;
    }

    public Post savePost(Post post) {
        validadePost(post);
        return repository.save(post);
    }
    private void validadePost(Post post) {
        if (!StringUtils.hasText(post.getTitle())) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (!StringUtils.hasText(post.getContent())) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
    }

}




