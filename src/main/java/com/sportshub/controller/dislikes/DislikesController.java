package com.sportshub.controller.dislikes;

import com.sportshub.dto.count.CountDto;

import com.sportshub.dto.dislikes.DislikesCreateDto;
import com.sportshub.dto.dislikes.DislikesDto;
import com.sportshub.dto.dislikes.DislikesUpdateDto;
import com.sportshub.service.dislikes.DislikesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("dislikes")
public class DislikesController {

    private final DislikesService dislikesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DislikesDto create(@RequestBody @Valid DislikesCreateDto dislikesDto) {
        return dislikesService.create(dislikesDto);
    }

    @GetMapping
    public List<DislikesDto> findAll(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "1000") int limit) {

        return dislikesService.findAll();
    }

    @GetMapping("{id}")
    public DislikesDto find(@PathVariable Long id) {
        return dislikesService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount(@RequestParam(defaultValue = "0") Long commId) {
        return dislikesService.getCount(commId);
    }

    @GetMapping("check")
    public CountDto getCheck(@RequestParam(defaultValue = "0") Long commId,
                             @RequestParam(defaultValue = "0") Long userId) {
        return dislikesService.getCheck(commId, userId);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody DislikesUpdateDto dislikesDto) {
        dislikesService.update(id, dislikesDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @RequestParam(defaultValue = "0") Long userId) {
        dislikesService.delete(id, userId);
    }
}