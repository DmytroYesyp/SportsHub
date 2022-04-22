package com.sportshub.controller.sport.kind;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.sport.kind.SportKindCreateDto;
import com.sportshub.dto.sport.kind.SportKindDto;
import com.sportshub.dto.sport.kind.SportKindUpdateDto;
import com.sportshub.service.sport.kind.SportKindService;
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
@RequestMapping("sport-kinds")
public class SportKindController {

    private final SportKindService sportKindService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SportKindDto create(@RequestBody @Valid SportKindCreateDto sportKindDto) {
        return sportKindService.create(sportKindDto);
    }

    @GetMapping
    public List<SportKindDto> findAll(@RequestParam(defaultValue = "1000") int limit,
                                      @RequestParam(defaultValue = "1000") int offset) {

        return sportKindService.findAll(limit,offset);
    }

    @GetMapping("{id}")
    public SportKindDto find(@PathVariable Long id) {
        return sportKindService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount() {
        return sportKindService.getCount();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody SportKindUpdateDto sportKindDto) {
        sportKindService.update(id, sportKindDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        sportKindService.delete(id);
    }
}