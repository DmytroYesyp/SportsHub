package com.sportshub.service.survey;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.survey.SurveyCreateDto;
import com.sportshub.dto.survey.SurveyDto;
import com.sportshub.dto.survey.SurveyUpdateDto;


import java.util.List;

public interface SurveyService {
    SurveyDto create(SurveyCreateDto surveyDto);
    List<SurveyDto> findAll(int page, int limit);
    CountDto getCount();
    SurveyDto find(Long id);
    void update(Long id, SurveyUpdateDto surveyDto);
    void delete(Long id);
}
