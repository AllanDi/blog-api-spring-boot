package com.allanDi.blogApi.service;

import com.allanDi.blogApi.model.Post;
import com.allanDi.blogApi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post savePost(Post post) {
        validadePost(post);
        return postRepository.save(post);
    }
    private void validadePost(Post post) {
        if (!StringUtils.hasText(post.getTitle())) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (!StringUtils.hasText(post.getContent())) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
    }
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}




