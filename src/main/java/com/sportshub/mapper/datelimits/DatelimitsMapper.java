package com.sportshub.mapper.datelimits;
import com.sportshub.dto.datelimits.DatelimitsCreateDto;
import com.sportshub.dto.datelimits.DatelimitsDto;
import com.sportshub.dto.datelimits.DatelimitsUpdateDto;
import com.sportshub.entity.datelimits.DatelimitsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DatelimitsMapper {
    DatelimitsEntity toEntity(DatelimitsCreateDto dto);
    DatelimitsEntity toEntity(DatelimitsUpdateDto dto);
    DatelimitsDto toDto(DatelimitsEntity entity);
    List<DatelimitsDto> toDtoList(List<DatelimitsEntity> entities);
}