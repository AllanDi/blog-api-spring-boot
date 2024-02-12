package com.allanDi.blogapi.controller;

import com.allanDi.blogapi.model.Post;
import com.allanDi.blogapi.repository.PostRepository;
import com.allanDi.blogapi.service.PostService;
import org.hibernate.event.spi.PostCommitDeleteEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postRepository.save(post);
    }

    @GetMapping
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
}
