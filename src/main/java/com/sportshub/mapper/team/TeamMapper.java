package com.sportshub.mapper.team;

import com.sportshub.dto.team.TeamCreateDto;
import com.sportshub.dto.team.TeamDto;
import com.sportshub.dto.team.TeamUpdateDto;
import com.sportshub.entity.team.TeamEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamEntity toEntity(TeamCreateDto dto);
    TeamEntity toEntity(TeamUpdateDto dto);
    TeamDto toDto(TeamEntity entity);
    List<TeamDto> toDtoList(List<TeamEntity> entities);
}

