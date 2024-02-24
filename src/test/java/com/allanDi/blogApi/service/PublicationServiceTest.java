package com.allanDi.blogApi.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.allanDi.blogApi.model.Publication;
import com.allanDi.blogApi.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PublicationServiceTest {

    @Mock
    Repository repository;

    @InjectMocks
    private PublicationService service;
    private Publication validPublication;

    @BeforeEach
    void setup(){
        validPublication = new Publication();
        validPublication.setTitle("Test title");
        validPublication.setContent("Valid content");
    }

    @Test
    void whenCreatePublicationWithValidPublication_thenSaveIsCalled(){

        service.createPublication(validPublication);
        verify(repository, times(1)).save(validPublication);
    }

    @Test
    void whenCreatePublicationWithInvalidPublication_thenThrowException(){

        Publication invalidPublication = new Publication();
        assertThrows(IllegalArgumentException.class, () ->
                service.createPublication(invalidPublication));

    }




    @Test
    void createPublication() {
    }

    @Test
    void readAllPublication() {
    }

    @Test
    void updatePublication() {
    }

    @Test
    void deletePublication() {
    }
}