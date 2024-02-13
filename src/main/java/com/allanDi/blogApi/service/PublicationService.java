package com.allanDi.blogApi.service;

import com.allanDi.blogApi.model.Publication;
import com.allanDi.blogApi.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PublicationService {
    private final Repository repository;

    @Autowired
    PublicationService(Repository repository) {
        this.repository = repository;
    }


    public Publication createPublication(Publication publication) {
        validateEmptyPublication(publication);
        return repository.save(publication);
    }

    public List<Publication> readAllPublication() {
        return repository.findAll();
    }

    public Publication updatePublication(Long id, Publication updatedPublication) {
        Publication existingPublication = validateExistingPublication(id);
        existingPublication.setTitle(updatedPublication.getTitle());
        existingPublication.setContent(updatedPublication.getContent());
        return createPublication(existingPublication);
    }

    public Publication deletePublication(Long id) {
        Publication existingPublication = validateExistingPublication(id);
        existingPublication.setActive(false);
        return createPublication(existingPublication);
    }

    private void validateEmptyPublication(Publication publication) {
        if (!StringUtils.hasText(publication.getTitle()) || !StringUtils.hasText(publication.getContent())) {
            throw new IllegalArgumentException("Title or Content cannot be empty");
        }
    }

    private Publication validateExistingPublication(Long id) {
        Publication existingPublication = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Publication id " + id + " not found"));
        return existingPublication;
    }
}
