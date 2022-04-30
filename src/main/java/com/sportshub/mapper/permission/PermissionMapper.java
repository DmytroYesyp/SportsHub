package com.sportshub.mapper.permission;

import com.sportshub.dto.permission.PermissionCreateDto;
import com.sportshub.dto.permission.PermissionDto;
import com.sportshub.dto.permission.PermissionUpdateDto;
import com.sportshub.entity.permission.PermissionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionEntity toEntity(PermissionCreateDto dto);
    PermissionEntity toEntity(PermissionUpdateDto dto);
    PermissionDto toDto(PermissionEntity entity);
    List<PermissionDto> toDtoList(List<PermissionEntity> entities);
}
