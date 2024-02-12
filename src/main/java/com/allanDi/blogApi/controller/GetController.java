package com.allanDi.blogApi.controller;

import com.allanDi.blogApi.model.Post;
import com.allanDi.blogApi.service.GetService;
import com.allanDi.blogApi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gets")
public class GetController {
    private final GetService getService;

    @Autowired
    public GetController(GetService getService) {
        this.getService = getService;
    }
    @GetMapping
    public List<Post> getAllPosts() {
        return getService.getAllPosts();
    }
}
