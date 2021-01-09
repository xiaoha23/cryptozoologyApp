package com.galvanize.cryptozoology.service;

import com.galvanize.cryptozoology.model.Animal;
import com.galvanize.cryptozoology.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal createAnimal(Animal animal) {
        Animal animalSaved = animalRepository.save(animal);
        return animalSaved;
    }

    public List<Animal> getAllAnimals() {
        List<Animal> animals = animalRepository.findAll();
        return animals;
    }
}
