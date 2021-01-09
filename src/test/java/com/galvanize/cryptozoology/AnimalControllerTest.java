package com.galvanize.cryptozoology;

import com.galvanize.cryptozoology.model.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AnimalControllerTest {

    @Autowired
    private MockMvc mvc;

//    galvanize.com/api/animal	POST	201 CREATED	Form submission to add a new animal.
    @Test
    public void createAnimal_shouldReturn201() throws Exception{
//        Animal animal = new Animal();
        String animalString = "";
        mvc
                .perform(
                        post("/api/animal")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(animalString))
                .andExpect(status().isCreated());
    }
}
