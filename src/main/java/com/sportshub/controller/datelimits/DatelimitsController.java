package com.sportshub.controller.datelimits;
import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.datelimits.DatelimitsCreateDto;
import com.sportshub.dto.datelimits.DatelimitsDto;
import com.sportshub.dto.datelimits.DatelimitsUpdateDto;
import com.sportshub.service.datelimits.DatelimitsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("datelimits")
public class DatelimitsController {

    private final DatelimitsService datelimitsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DatelimitsDto create(@RequestBody @Valid DatelimitsCreateDto datelimitsDto) {
        return datelimitsService.create(datelimitsDto);
    }

    @GetMapping
    public List<DatelimitsDto> findAll(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "1000") int limit) {

        return datelimitsService.findAll();
    }

    @GetMapping("{id}")
    public DatelimitsDto find(@PathVariable Long id) {
        return datelimitsService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount() {
        return datelimitsService.getCount();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody DatelimitsUpdateDto datelimitsDto) {
        datelimitsService.update(id, datelimitsDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        datelimitsService.delete(id);
    }

}