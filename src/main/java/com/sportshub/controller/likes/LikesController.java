package com.sportshub.controller.likes;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.count.CountLikesDto;
import com.sportshub.dto.likes.LikesCreateDto;
import com.sportshub.dto.likes.LikesDto;
import com.sportshub.dto.likes.LikesUpdateDto;
import com.sportshub.service.likes.LikesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("likes")
public class LikesController {

    private final LikesService likesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LikesDto create(@RequestBody @Valid LikesCreateDto likesDto) {
        return likesService.create(likesDto);
    }

    @GetMapping
    public List<LikesDto> findAll(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "1000") int limit) {

        return likesService.findAll();
    }

    @GetMapping("{id}")
    public LikesDto find(@PathVariable Long id) {
        return likesService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount(@RequestParam(defaultValue = "0") Long commId) {
        return likesService.getCount(commId);
    }

    @GetMapping("check")
    public CountDto getCheck(@RequestParam(defaultValue = "0") Long commId,
                             @RequestParam(defaultValue = "0") Long userId) {
        return likesService.getCheck(commId, userId);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody LikesUpdateDto likesDto) {
        likesService.update(id, likesDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @RequestParam(defaultValue = "0") Long userId) {
        likesService.delete(id, userId);
    }
}