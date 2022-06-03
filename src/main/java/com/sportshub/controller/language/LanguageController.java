package com.sportshub.controller.language;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.language.LanguageCreateDto;
import com.sportshub.dto.language.LanguageDto;
import com.sportshub.dto.language.LanguageUpdateDto;
import com.sportshub.service.language.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("language")
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LanguageDto create(@RequestBody @Valid LanguageCreateDto languageDto) {
        return languageService.create(languageDto);
    }

    @GetMapping
    public List<LanguageDto> findAll(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "1000") int limit) {

        return languageService.findAll();
    }

    @GetMapping("{id}")
    public LanguageDto find(@PathVariable Long id) {
        return languageService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount() {
        return languageService.getCount();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody LanguageUpdateDto languageDto) {
        languageService.update(id, languageDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        languageService.delete(id);
    }

}
