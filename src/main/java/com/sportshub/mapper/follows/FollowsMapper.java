package com.sportshub.mapper.follows;
import com.sportshub.dto.follows.FollowsCreateDto;
import com.sportshub.dto.follows.FollowsDto;
import com.sportshub.dto.follows.FollowsUpdateDto;
import com.sportshub.entity.follows.FollowsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FollowsMapper {
    FollowsEntity toEntity(FollowsCreateDto dto);
    FollowsEntity toEntity(FollowsUpdateDto dto);
    FollowsDto toDto(FollowsEntity entity);
    List<FollowsDto> toDtoList(List<FollowsEntity> entities);
}