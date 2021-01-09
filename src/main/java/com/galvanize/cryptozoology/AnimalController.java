package com.galvanize.cryptozoology;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {

    @PostMapping("/api/animal")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnimal() {

    }
}
