package com.allanDi.blogApi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

import com.allanDi.blogApi.controller.GetController;
import com.allanDi.blogApi.model.Publication;
import com.allanDi.blogApi.service.PublicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GetController.class)
public class GetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicationService publicationService;

    @Mock
    private Publication publication;

    @Test
    public void readAllPublication_ReturnsPublicationsList() throws Exception {
        Publication pub1 = new Publication();
        Publication pub2 = new Publication();
        List<Publication> publications = Arrays.asList(pub1, pub2);

        when(publicationService.readAllPublication()).thenReturn(publications);

        mockMvc.perform(get("/api/get"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(publications.size()));
    }

    @Test
    public void findByPublicationId_ReturnsPublication() throws Exception {
        Long id = 1L;
        Publication publication = new Publication();
        publication.setId(id);

        when(publicationService.findByPublicationId(id)).thenReturn(publication);

        mockMvc.perform(get("/api/get/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }
}
