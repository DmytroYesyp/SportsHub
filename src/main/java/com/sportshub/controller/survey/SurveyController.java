package com.sportshub.controller.survey;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.survey.SurveyCreateDto;
import com.sportshub.dto.survey.SurveyDto;
import com.sportshub.dto.survey.SurveyUpdateDto;
import com.sportshub.service.survey.SurveyService;
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
@RequestMapping("surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyDto create(@RequestBody @Valid SurveyCreateDto surveyDto) {
        return surveyService.create(surveyDto);
    }

    @GetMapping
    public List<SurveyDto> findAll(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "1000") int limit) {

        return surveyService.findAll(page, limit);
    }

    @GetMapping("{id}")
    public SurveyDto find(@PathVariable Long id) {
        return surveyService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount() {
        return surveyService.getCount();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody SurveyUpdateDto surveyDto) {
        surveyService.update(id, surveyDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        surveyService.delete(id);
    }
}
