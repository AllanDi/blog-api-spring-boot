package com.allanDi.blogApi.repository;

import com.allanDi.blogApi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Post, Long> {
}
