package com.allanDi.blogApi.controller;

import com.allanDi.blogApi.model.Publication;
import com.allanDi.blogApi.service.PublicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DeleteController.class)
public class DeleteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicationService publicationService;

    @Test
    public void whenDeletePublication_thenShouldReturn200() throws Exception {
        Long id = 1L;
        Publication mockPublication = new Publication();
        Mockito.when(publicationService.deletePublication(id)).thenReturn(mockPublication);

        mockMvc.perform(delete("/api/delete/{id}", id))
                .andExpect(status().isOk());

        Mockito.verify(publicationService, Mockito.times(1)).deletePublication(id);
    }
}
