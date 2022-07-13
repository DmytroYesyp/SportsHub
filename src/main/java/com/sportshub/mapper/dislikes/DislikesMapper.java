package com.sportshub.mapper.dislikes;
import com.sportshub.dto.dislikes.DislikesCreateDto;
import com.sportshub.dto.dislikes.DislikesDto;
import com.sportshub.dto.dislikes.DislikesUpdateDto;
import com.sportshub.entity.dislikes.DislikesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DislikesMapper {
    DislikesEntity toEntity(DislikesCreateDto dto);
    DislikesEntity toEntity(DislikesUpdateDto dto);
    DislikesDto toDto(DislikesEntity entity);
    List<DislikesDto> toDtoList(List<DislikesEntity> entities);
}