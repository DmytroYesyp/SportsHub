package com.sportshub.mapper.likes;

import com.sportshub.dto.likes.LikesCreateDto;
import com.sportshub.dto.likes.LikesDto;
import com.sportshub.dto.likes.LikesUpdateDto;
import com.sportshub.entity.likes.LikesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikesMapper {
    LikesEntity toEntity(LikesCreateDto dto);
    LikesEntity toEntity(LikesUpdateDto dto);
    LikesDto toDto(LikesEntity entity);
    List<LikesDto> toDtoList(List<LikesEntity> entities);
}