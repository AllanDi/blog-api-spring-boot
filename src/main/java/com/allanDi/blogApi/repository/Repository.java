package com.allanDi.blogApi.repository;

import com.allanDi.blogApi.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Publication, Long> {

}
