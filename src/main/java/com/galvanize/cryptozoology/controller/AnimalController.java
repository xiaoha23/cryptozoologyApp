package com.galvanize.cryptozoology.controller;

import com.galvanize.cryptozoology.service.AnimalService;
import com.galvanize.cryptozoology.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    private AnimalService animalService;

    AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal createAnimal(@RequestBody Animal animal) {

        return animalService.createAnimal(animal);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> getAllAnimal() {

        return animalService.getAllAnimals();
    }
}
