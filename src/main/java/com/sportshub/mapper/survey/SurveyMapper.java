package com.sportshub.mapper.survey;


import com.sportshub.dto.survey.SurveyCreateDto;
import com.sportshub.dto.survey.SurveyDto;
import com.sportshub.dto.survey.SurveyUpdateDto;
import com.sportshub.entity.survey.SurveyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurveyMapper {
    SurveyEntity toEntity(SurveyCreateDto dto);
    SurveyEntity toEntity(SurveyUpdateDto dto);
    SurveyDto toDto(SurveyEntity entity);
    List<SurveyDto> toDtoList(List<SurveyEntity> entities);
}
