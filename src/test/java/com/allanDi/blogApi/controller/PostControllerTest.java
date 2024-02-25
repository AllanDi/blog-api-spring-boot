package com.allanDi.blogApi.controller;

import com.allanDi.blogApi.controller.PostController;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicationService publicationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenCreatePublication_thenPublicationShouldBeCreated() throws Exception {
        Publication newPublication = new Publication(
                1L, true, "Test title", "Test content");
        Publication savedPublication = new Publication(
                1L, true, "Test title", "Test content");

        when(publicationService.createPublication(any(Publication.class))).thenReturn(savedPublication);

        mockMvc.perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPublication)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(newPublication.getTitle()))
                .andExpect(jsonPath("$.content").value(newPublication.getContent()));

        verify(publicationService, times(1)).createPublication(any(Publication.class));
    }
}
