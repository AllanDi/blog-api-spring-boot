package com.allanDi.blogApi.controller;

import com.allanDi.blogApi.model.Publication;
import com.allanDi.blogApi.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delete")
public class DeleteController {

    private final PublicationService publicationService;

    @Autowired
    public DeleteController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @DeleteMapping("/{id}")
    public Publication deletePublication(
            @PathVariable Long id){
      return publicationService.deletePublication(id);
    }

}
