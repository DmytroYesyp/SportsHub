package com.sportshub.mapper.newsletter;
import com.sportshub.dto.newsletter.NewsletterCreateDto;
import com.sportshub.dto.newsletter.NewsletterDto;
import com.sportshub.dto.newsletter.NewsletterUpdateDto;
import com.sportshub.entity.newsletter.NewsletterEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsletterMapper {
    NewsletterEntity toEntity(NewsletterCreateDto dto);
    NewsletterEntity toEntity(NewsletterUpdateDto dto);
    NewsletterDto toDto(NewsletterEntity entity);
    List<NewsletterDto> toDtoList(List<NewsletterEntity> entities);
}