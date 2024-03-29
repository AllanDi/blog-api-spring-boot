package com.allanDi.blogApi.controller;

import com.allanDi.blogApi.model.Publication;
import com.allanDi.blogApi.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/get")
public class GetController {
    private final PublicationService publicationService;

    @Autowired
    public GetController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping
    public List<Publication> readAllPublication() {
        return publicationService.readAllPublication();
    }

    @GetMapping("/{id}")
    public Publication findByPublicationId(@PathVariable Long id) {
        return publicationService.findByPublicationId(id);
    }

}
