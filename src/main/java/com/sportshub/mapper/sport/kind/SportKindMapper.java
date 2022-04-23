package com.sportshub.mapper.sport.kind;
import com.sportshub.dto.sport.kind.SportKindCreateDto;
import com.sportshub.dto.sport.kind.SportKindDto;
import com.sportshub.dto.sport.kind.SportKindUpdateDto;
import com.sportshub.entity.sport.kind.SportKindEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SportKindMapper {
    SportKindEntity toEntity(SportKindCreateDto dto);
    SportKindEntity toEntity(SportKindUpdateDto dto);
    SportKindDto toDto(SportKindEntity entity);
    List<SportKindDto> toDtoList(List<SportKindEntity> entities);
}
