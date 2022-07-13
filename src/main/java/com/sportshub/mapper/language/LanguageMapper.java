package com.sportshub.mapper.language;


import com.sportshub.dto.language.LanguageCreateDto;
import com.sportshub.dto.language.LanguageDto;
import com.sportshub.dto.language.LanguageUpdateDto;
import com.sportshub.entity.language.LanguageEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    LanguageEntity toEntity(LanguageCreateDto dto);
    LanguageEntity toEntity(LanguageUpdateDto dto);
    LanguageDto toDto(LanguageEntity entity);
    List<LanguageDto> toDtoList(List<LanguageEntity> entities);
}
