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

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postService.savePost(post);
    }

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }
}
