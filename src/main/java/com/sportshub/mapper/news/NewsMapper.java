package com.sportshub.mapper.news;


import com.sportshub.dto.news.NewsCreateDto;
import com.sportshub.dto.news.NewsDto;
import com.sportshub.dto.news.NewsUpdateDto;
import com.sportshub.entity.news.NewsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", implementationName = "leagueNewsMapper")
public interface NewsMapper {
    @Mapping(target = "league.id", source = "leagueId")
    NewsEntity toEntity(NewsCreateDto dto);

    @Mapping(target = "league.id", source = "leagueId")
    NewsEntity toEntity(NewsUpdateDto dto);

    @Mapping(target = "leagueId", source = "league.id")
    NewsDto toDto(NewsEntity entity);

    List<NewsDto> toDtoList(List<NewsEntity> entities);
}