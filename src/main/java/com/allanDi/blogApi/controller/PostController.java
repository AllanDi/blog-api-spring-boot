package com.allanDi.blogApi.controller;

import com.allanDi.blogApi.model.Publication;
import com.allanDi.blogApi.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PublicationService publicationService;

    @Autowired
    public PostController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping
    public Publication createPublication(
            @RequestBody Publication publication) {
        return publicationService.createPublication(publication);
    }

}
