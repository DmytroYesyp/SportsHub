package com.sportshub.controllers;

import java.util.List;
import java.util.Optional;

import com.sportshub.models.Team;
import com.sportshub.repo.TeamRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TeamController {

    private final TeamRepository repository;

    TeamController(TeamRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/team")
    List<Team> all() {
        return repository.findAll();
    }

    @PostMapping("/team")
    public Team newTeam(@RequestBody Team newTeam) {
        return repository.save(newTeam);
    }


    @GetMapping("/team/{id}")
    public Optional<Team> one(@PathVariable Long id) {

        return repository.findById(id);

    }

    @PutMapping("/team/{id}")
    public Team replaceTeam(@RequestBody Team newTeam, @PathVariable Long id) {

        return repository.findById(id)
                .map(team -> {
                    team.setName(newTeam.getName());
                    team.setCoach(newTeam.getCoach());
                    team.setState(newTeam.getState());
                    return repository.save(team);
                })
                .orElseGet(() -> {
                    newTeam.setId(id);
                    return repository.save(newTeam);
                });
    }

    @DeleteMapping("/team/{id}")
    public void deleteTeam(@PathVariable Long id) {
        repository.deleteById(id);
    }
}