package com.allanDi.blogApi.service;

import com.allanDi.blogApi.model.Publication;
import com.allanDi.blogApi.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {
    private final Repository repository;
    @Autowired
    PublicationService(Repository repository) {this.repository = repository;}



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
        return repository.save(existingPublication);
    }

    public Publication deletePublication(Long id){
        Publication publication = validateExistingPublication(id);
        publication.setActive(false);
        return repository.save(publication);
    }

    private Publication validateExistingPublication(Long id) {
        Optional<Publication> optionalPublication = repository.findById(id);
        if (!optionalPublication.isPresent()) {
            throw new IllegalArgumentException("Publication id " + id + " not found");
        }
        return optionalPublication.get();
    }

    private void validateEmptyPublication(Publication publication) {
        if (!StringUtils.hasText(publication.getTitle()) || !StringUtils.hasText(publication.getContent())) {
            throw new IllegalArgumentException("Title or Content cannot be empty");
        }
    }
}
