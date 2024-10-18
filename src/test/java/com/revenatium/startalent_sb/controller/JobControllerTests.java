package com.revenatium.startalent_sb.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
class JobControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("debería devolver 'Hello World'")
    void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/jobs"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello World"));
    }

    @Test
    @DisplayName("debería devolver '404 Not Found' para un endpoint inválido")
    void shouldReturnNotFoundForInvalidEndpoint() throws Exception {
        mockMvc.perform(get("/invalid"))
            .andExpect(status().isNotFound());
    }
}
