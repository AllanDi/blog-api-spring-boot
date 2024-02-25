package com.allanDi.blogApi.controller;

import com.allanDi.blogApi.model.Publication;
import com.allanDi.blogApi.service.PublicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PutController.class)
public class PutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicationService publicationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenUpdatePublication_thenPublicationShouldBeUpdated() throws Exception {
        Long id = 1L;
        Publication updatedPublication = new Publication(
                1L, true, "Test title", "Test content");
        Publication savedPublication = new Publication(
                1L, true, "Test title", "Test content");

        when(publicationService.updatePublication(eq(id), any(Publication.class)))
                .thenReturn(savedPublication);

        mockMvc.perform(put("/api/put/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPublication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updatedPublication.getTitle()))
                .andExpect(jsonPath("$.content").value(updatedPublication.getContent()));

        verify(publicationService, times(1)).updatePublication(eq(id), any(Publication.class));
    }
}
