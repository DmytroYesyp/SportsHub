package com.sportshub.controllers;

import com.sportshub.models.Kinds_of_sport;
import com.sportshub.repo.Kinds_of_sportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Kinds_of_sportController {

    private final Kinds_of_sportRepository repository;

    Kinds_of_sportController(Kinds_of_sportRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/kinds_of_sport")
    List<Kinds_of_sport> all() {
        return repository.findAll();
    }

    @PostMapping("/kinds_of_sport")
    public Kinds_of_sport newKinds_of_sport(@RequestBody Kinds_of_sport newKinds_of_sport) {
        return repository.save(newKinds_of_sport);
    }


    @GetMapping("/kinds_of_sport/{id}")
    public Optional<Kinds_of_sport> one(@PathVariable Long id) {

        return repository.findById(id);

    }

    @PutMapping("/kinds_of_sport/{id}")
    public Kinds_of_sport replaceKinds_of_sport(@RequestBody Kinds_of_sport newKinds_of_sport, @PathVariable Long id) {

        return repository.findById(id)
                .map(kinds_of_sport -> {
                    kinds_of_sport.setName(newKinds_of_sport.getName());
                    return repository.save(kinds_of_sport);
                })
                .orElseGet(() -> {
                    newKinds_of_sport.setId(id);
                    return repository.save(newKinds_of_sport);
                });
    }

    @DeleteMapping("/kinds_of_sport/{id}")
    public void deleteKinds_of_sport(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
