package com.galvanize.cryptozoology;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.cryptozoology.model.Animal;
import com.galvanize.cryptozoology.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

    @MockBean
    private AnimalRepository animalRepository;

    @Test
    public void createAnimal_shouldReturn201_withOneAnimal() throws Exception{
        Animal animal = new Animal("tiger", "walking");
        Animal savedAnimal = new Animal("tiger", "walking");
        savedAnimal.setId(1);
        String animalString = mapper.writeValueAsString(animal);
        when(animalRepository.save(any(Animal.class))).thenReturn(savedAnimal);
        mvc
                .perform(
                        post("/api/animal")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(animalString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("tiger"))
                .andExpect(jsonPath("$.type").value("walking"));
    }

    //galvanize.com/api/animal/all    |   GET         |   200     |OK         | Get All animals
    @Test
    public void getAllAnimals() throws Exception{
        setupDb();
                mvc
                .perform(
                        get("/api/animal/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))

        ;
    }



    //Utility Method
    void setupDb(){
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("tiger","walking"));
        animals.add(new Animal("dodo", "flying"));
        animals.add(new Animal("sabertooth","walking"));

        when(animalRepository.findAll()).thenReturn(animals);
    }
}
