package com.galvanize.cryptozoology;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.cryptozoology.model.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AnimalControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

//    galvanize.com/api/animal	POST	201 CREATED	Form submission to add a new animal.
    @Test
    public void createAnimal_shouldReturn201() throws Exception{
        Animal animal = new Animal("tiger", "walking");
        String animalString = mapper.writeValueAsString(animal);
        mvc
                .perform(
                        post("/api/animal")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(animalString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("tiger"))
                .andExpect(jsonPath("$.type").value("walking"));
    }
}
