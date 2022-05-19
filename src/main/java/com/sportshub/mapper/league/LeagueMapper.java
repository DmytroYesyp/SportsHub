package com.sportshub.mapper.league;

import com.sportshub.dto.league.LeagueContentDto;
import com.sportshub.dto.league.LeagueDto;
import com.sportshub.entity.league.LeagueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeagueMapper {

    @Mapping(target = "sportKind.id", source = "sportKindId")
    LeagueEntity toEntity(LeagueContentDto dto);

    LeagueDto toDto(LeagueEntity entity);

    List<LeagueDto> toDtoList(List<LeagueEntity> entities);
}
