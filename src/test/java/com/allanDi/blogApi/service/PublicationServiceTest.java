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

import javax.swing.text.html.Option;
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
    @Mock
    private Publication updatedPublication;

    @InjectMocks
    private PublicationService service;


    @BeforeEach
    void setup() {
        validActivePublication = new Publication(
                1L, true, "Test title", "Test content");
        validNonActivePublication = new Publication(
                2L, false, "Test title", "Test content");
        updatedPublication = new Publication(
                3L, true, "Test updated title", "Test updated content");
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
        Long invalidId = 99L;

        when(repository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                service.findByPublicationId(invalidId));
    }

    @Test
    void whenUpdatePublication_thenPublicationShouldBeUpdated(){
        Long validId = 1L;
        when(repository.findById(validId)).thenReturn(Optional.of(validActivePublication));
        when(repository.save(any(Publication.class))).thenReturn(validActivePublication);

        Publication result = service.updatePublication(validId, updatedPublication);

        assertEquals("Test updated title",result.getTitle());
        assertEquals("Test updated content",result.getContent());

        verify(repository, times(1)).save(validActivePublication);
    }

    @Test
    void whenDeletePublication_thenPublicationShouldBeDeactivate(){
        Long validId = 1L;

        when(repository.findById(validId)).thenReturn(Optional.of(validActivePublication));
        when(repository.save(any(Publication.class))).thenAnswer(i -> i.getArgument(0));

        Publication result = service.deletePublication(validId);

        assertFalse(result.isActive());
        verify(repository, times(1)).save(validActivePublication);
    }

}