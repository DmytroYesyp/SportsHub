package com.sportshub.mapper.team;

import com.sportshub.dto.team.TeamCreateDto;
import com.sportshub.dto.team.TeamDto;
import com.sportshub.dto.team.TeamUpdateDto;
import com.sportshub.entity.team.TeamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    @Mapping(target = "league.id", source = "leagueId")
    TeamEntity toEntity(TeamCreateDto dto);

    @Mapping(target = "league.id", source = "leagueId")
    TeamEntity toEntity(TeamUpdateDto dto);

    TeamDto toDto(TeamEntity entity);

    List<TeamDto> toDtoList(List<TeamEntity> entities);
}

