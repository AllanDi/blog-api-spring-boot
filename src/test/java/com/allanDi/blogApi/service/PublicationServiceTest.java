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


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PublicationServiceTest {

    @Mock
    private Repository repository;

    @Mock
    private Publication validPublication;

    @InjectMocks
    private PublicationService service;


    @BeforeEach
    void setup() {
        validPublication = new Publication();
        validPublication.setTitle("Test title");
        validPublication.setContent("Valid content");
    }

    @Test
    void whenCreatePublicationWithValidPublication_thenSaveIsCalled() {
        service.createPublication(validPublication);
        verify(repository, times(1)).save(validPublication);
    }

    @Test
    void whenCreatePublicationWithInvalidPublication_thenThrowException() {
        Publication invalidPublication = new Publication();
        assertThrows(IllegalArgumentException.class, () ->
                service.createPublication(invalidPublication));
    }

    @Test
    void whenReadAllPublication_thenAllPublicationAreReturned() {
        Publication publication1 =
                new Publication(1L, true, "Title publication1",
                        "Content publication1");
        Publication publication2 =
                new Publication(2L, true, "Title publication2",
                        "Content publication2");
        List<Publication> mockListPublication = Arrays.asList(publication1, publication2);

        when(repository.findAll()).thenReturn(mockListPublication);

        List<Publication> returnedListPublication = service.readAllPublication();

        assertNotNull(returnedListPublication);
        assertEquals(mockListPublication.size(),returnedListPublication.size());
        assertTrue(returnedListPublication.containsAll(mockListPublication));
    }

}