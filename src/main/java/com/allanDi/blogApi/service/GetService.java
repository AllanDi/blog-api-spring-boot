package com.allanDi.blogApi.service;

import com.allanDi.blogApi.model.Post;
import com.allanDi.blogApi.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetService {

    private final Repository repository;

    @Autowired
    GetService(Repository repository) {
        this.repository = repository;
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }
}
