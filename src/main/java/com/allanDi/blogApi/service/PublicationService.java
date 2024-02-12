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
    PublicationService(Repository repository) {
        this.repository = repository;
    }

    public Publication savePublication(Publication publication) {
        validadePublication(publication);
        return repository.save(publication);
    }

    private void validadePublication(Publication publication) {
        if (!StringUtils.hasText(publication.getTitle())) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (!StringUtils.hasText(publication.getContent())) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
    }

    public List<Publication> getAllPublication() {
        return repository.findAll();
    }

    public Publication updatePublication(Long id, Publication updatedPublication) {
        Optional<Publication> existingPostOptional = repository.findById(id);
        if (existingPostOptional.isPresent()) {
            Publication existingPost = existingPostOptional.get();
            existingPost.setTitle(updatedPublication.getTitle());
            existingPost.setContent(updatedPublication.getContent());
            return repository.save(existingPost);
        } else {
            return null;
        }
    }
}
