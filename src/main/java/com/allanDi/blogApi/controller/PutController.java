package com.allanDi.blogApi.controller;

import com.allanDi.blogApi.model.Publication;
import com.allanDi.blogApi.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/put")
public class PutController {
    private final PublicationService publicationService;

    @Autowired
    public PutController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PutMapping("/{id}")
    public Publication updatePublication(@PathVariable Long id, @RequestBody Publication updatedPublication) {
        return publicationService.updatePublication(id, updatedPublication);
    }
}
