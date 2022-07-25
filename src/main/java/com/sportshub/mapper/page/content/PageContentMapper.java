package com.sportshub.mapper.page.content;

import com.sportshub.dto.page.content.PageContentCreateDto;
import com.sportshub.dto.page.content.PageContentDto;
import com.sportshub.dto.page.content.PageContentUpdateDto;
import com.sportshub.entity.page.content.PageContentEntity;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageContentMapper {
    PageContentEntity toEntity(PageContentCreateDto dto);

    PageContentEntity toEntity(PageContentUpdateDto dto);

    PageContentDto toDto(PageContentEntity entity);

    List<PageContentDto> toDtoList(List<PageContentEntity> entities);
}
