package com.sportshub.controller.league;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.league.LeagueContentDto;
import com.sportshub.dto.league.LeagueDto;
import com.sportshub.service.league.LeagueService;
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
@RequestMapping("leagues")
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeagueDto create(@RequestBody @Valid LeagueContentDto leagueDto) {
        return leagueService.create(leagueDto);
    }

    @GetMapping
    public List<LeagueDto> findAll(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "1000") int limit) {

        return leagueService.findAll(page, limit);
    }

    @GetMapping("{id}")
    public LeagueDto find(@PathVariable Long id) {
        return leagueService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount() {
        return leagueService.getCount();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody LeagueContentDto leagueDto) {
        leagueService.update(id, leagueDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        leagueService.delete(id);
    }
}
