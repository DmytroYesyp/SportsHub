package com.sportshub.mapper.league;

import com.sportshub.dto.league.LeagueCreateDto;
import com.sportshub.dto.league.LeagueDto;
import com.sportshub.dto.league.LeagueUpdateDto;
import com.sportshub.entity.league.LeagueEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeagueMapper {
    LeagueEntity toEntity(LeagueCreateDto dto);
    LeagueEntity toEntity(LeagueUpdateDto dto);
    LeagueDto toDto(LeagueEntity entity);
    List<LeagueDto> toDtoList(List<LeagueEntity> entities);
}
