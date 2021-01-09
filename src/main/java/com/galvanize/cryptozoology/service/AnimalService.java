package com.galvanize.cryptozoology.service;

import com.galvanize.cryptozoology.model.Animal;
import com.galvanize.cryptozoology.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }
}