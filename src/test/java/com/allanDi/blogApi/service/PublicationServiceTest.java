package com.allanDi.blogApi.service;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PublicationServiceTest {

    @Mock
    private Repository repository;
    @Mock
    private Publication validActivePublication;
    @Mock
    private Publication validNonActivePublication;
    @Mock
    private Publication emptyPublication;

    @InjectMocks
    private PublicationService service;


    @BeforeEach
    void setup() {
        validActivePublication = new Publication(
                1L, true, "Test title", "Test content");
        validNonActivePublication = new Publication(
                2L, false, "Test title", "Test content");
        emptyPublication = new Publication();
    }

    @Test
    void whenCreatePublicationWithValidPublication_thenSaveIsCalled() {
        service.createPublication(validActivePublication);
        verify(repository, times(1)).save(validActivePublication);
    }

    @Test
    void whenCreatePublicationWithInvalidPublication_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                service.createPublication(emptyPublication));
    }

    @Test
    void whenReadAllPublication_thenAllPublicationAreReturned() {
        List<Publication> mockListPublication = Arrays.asList(
                validActivePublication, validNonActivePublication);

        when(repository.findAll()).thenReturn(mockListPublication);

        List<Publication> returnedListPublication = service.readAllPublication();

        assertNotNull(returnedListPublication);
        assertEquals(mockListPublication.size(),returnedListPublication.size());
        assertTrue(returnedListPublication.containsAll(mockListPublication));
    }

    @Test
    void whenReadPublicationByValidId_thenPublicationByValidIdAreReturned(){
        Long validId = 1L;
        when(repository.findById(validId))
                .thenReturn(Optional.of(validActivePublication));

        Publication foundPublication = service.findByPublicationId(validId);

        assertEquals(validActivePublication, foundPublication);
    }

    @Test
    void whenReadPublicationByInvalidId_thenExceptionIsThrows(){
        Long invalidId = 2L;

        when(repository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                service.findByPublicationId(invalidId));
    }

}