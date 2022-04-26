package com.sportshub.mapper.comment;

import com.sportshub.dto.comment.CommentCreateDto;
import com.sportshub.dto.comment.CommentDto;
import com.sportshub.dto.comment.CommentUpdateDto;
import com.sportshub.entity.comment.CommentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentEntity toEntity(CommentCreateDto dto);
    CommentEntity toEntity(CommentUpdateDto dto);
    CommentDto toDto(CommentEntity entity);
    List<CommentDto> toDtoList(List<CommentEntity> entities);
}
