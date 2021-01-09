package com.galvanize.cryptozoology;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.cryptozoology.model.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AnimalControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    //    galvanize.com/api/animal	POST	201 CREATED	Form submission to add a new animal.
    @Test
    public void createAnimal_shouldReturn201_withOneAnimal() throws Exception{
        Animal animal = new Animal("tiger", "walking");
        String animalString = mapper.writeValueAsString(animal);
        mvc
                .perform(
                        post("/api/animal")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(animalString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("tiger"))
                .andExpect(jsonPath("$.type").value("walking"));
    }

    @Test
    public void createAnimal_TestAutoIncrementID_withMultipleAnimal() throws Exception{
        Animal animal = new Animal("tiger", "walking");
        String animalString = mapper.writeValueAsString(animal);
        mvc
                .perform(
                        post("/api/animal")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(animalString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("tiger"))
                .andExpect(jsonPath("$.type").value("walking"));


        mvc
                .perform(
                        post("/api/animal")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(new Animal("lion", "walking"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("lion"))
                .andExpect(jsonPath("$.type").value("walking"));
    }

    //galvanize.com/api/animal/all    |   GET         |   200     |OK         | Get All animals
    @Test
    public void getAllAnimals() throws Exception{
        mvc
                .perform(
                        get("/api/animal/all"))
                .andExpect(status().isOk());
    }

}
