package com.sportshub.controller.team;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.team.TeamCreateDto;
import com.sportshub.dto.team.TeamDto;
import com.sportshub.dto.team.TeamUpdateDto;
import com.sportshub.service.team.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("teams")
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDto create(@RequestBody @Valid TeamCreateDto teamDto) {
        return teamService.create(teamDto);
    }

    @GetMapping
    public List<TeamDto> findAll(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "1000") int limit) {

        return teamService.findAll(page, limit);
    }

    @GetMapping("{id}")
    public TeamDto find(@PathVariable Long id) {
        return teamService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount() {
        return teamService.getCount();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody TeamUpdateDto teamDto) {
        teamService.update(id, teamDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        teamService.delete(id);
    }
}
