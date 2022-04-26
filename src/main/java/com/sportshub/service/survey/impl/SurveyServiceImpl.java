package com.sportshub.service.survey.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.survey.SurveyCreateDto;
import com.sportshub.dto.survey.SurveyDto;
import com.sportshub.dto.survey.SurveyUpdateDto;
import com.sportshub.entity.survey.SurveyEntity;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.survey.SurveyMapper;
import com.sportshub.repository.survey.SurveyRepository;
import com.sportshub.service.survey.SurveyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    @Override
    public SurveyDto create(SurveyCreateDto surveyDto) {
        SurveyEntity surveyEntity = surveyMapper.toEntity(surveyDto);
        surveyEntity = surveyRepository.save(surveyEntity);
        return surveyMapper.toDto(surveyEntity);
    }

    @Override
    public List<SurveyDto> findAll(int page, int limit) {
        List<SurveyEntity> surveyEntities = surveyRepository.findAllSurveys(PageRequest.of(page - 1, limit));
        return surveyMapper.toDtoList(surveyEntities);
    }

    @Override
    public CountDto getCount() {
        int sportKindsCount = (int) surveyRepository.count();
        return new CountDto(sportKindsCount);
    }

    @Override
    public SurveyDto find(Long id) {
        SurveyEntity surveyEntity = surveyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Surveys not found!"));
        return surveyMapper.toDto(surveyEntity);
    }

    @Override
    @Transactional
    public void update(Long id, SurveyUpdateDto surveyDto) {
        SurveyEntity surveyEntity = surveyMapper.toEntity(surveyDto);
        int affectedRaws = surveyRepository.update(id, surveyEntity);
        if (affectedRaws == 0) {
            new NotFoundException("Surveys not found!");
        }

        surveyRepository.update(id, surveyEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = surveyRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("Surveys not found!");
        }
    }
}
