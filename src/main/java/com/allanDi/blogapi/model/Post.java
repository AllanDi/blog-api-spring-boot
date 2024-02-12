package com.allanDi.blogapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY);
    private long id;

    private String title;
    private String content;
    private LocalDateTime creatAt;
    private LocalDateTime updatedAt;



}
