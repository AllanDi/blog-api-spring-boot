package com.allanDi.blogapi.service;

import com.allanDi.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

}
