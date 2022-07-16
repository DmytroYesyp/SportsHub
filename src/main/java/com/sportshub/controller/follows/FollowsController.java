package com.sportshub.controller.follows;
import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.follows.FollowsCreateDto;
import com.sportshub.dto.follows.FollowsDto;
import com.sportshub.dto.follows.FollowsUpdateDto;
import com.sportshub.service.follows.FollowsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("follows")
public class FollowsController {

    private final FollowsService followsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FollowsDto create(@RequestBody @Valid FollowsCreateDto followsDto) {
        return followsService.create(followsDto);
    }

    @GetMapping
    public List<FollowsDto> findAll(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "1000") int limit,
                                    @RequestParam(defaultValue = "0") Long userId) {

        return followsService.findAll(userId);
    }

    @GetMapping("{id}")
    public FollowsDto find(@PathVariable Long id) {
        return followsService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount() {
        return followsService.getCount();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody FollowsUpdateDto followsDto) {
        followsService.update(id, followsDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @RequestParam(defaultValue = "0") Long teamId) {
        followsService.delete(id, teamId);
    }

}