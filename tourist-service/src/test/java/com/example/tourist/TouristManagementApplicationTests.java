package com.example.tourist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TouristManagementApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testDirectJwtTokenGenerationOutput() throws Exception {
        mockMvc.perform(get("/api/token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Name").value("Alice"))
                .andExpect(jsonPath("$.['E-mail']").value("Alice@gmail.com"))
                .andExpect(jsonPath("$.token").value("18"))
                .andExpect(jsonPath("$.key").value("valid"));
    }

    @Test
    void testGenerateTokenPostEndpoint() throws Exception {
        mockMvc.perform(post("/api/generate-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Name").value("Alice"))
                .andExpect(jsonPath("$.['E-mail']").value("Alice@gmail.com"))
                .andExpect(jsonPath("$.token").value("18"))
                .andExpect(jsonPath("$.key").value("valid"));
    }
}
