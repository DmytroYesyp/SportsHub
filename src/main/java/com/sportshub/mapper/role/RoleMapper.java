package com.sportshub.mapper.role;

import com.sportshub.dto.role.RoleCreateDto;
import com.sportshub.dto.role.RoleDto;
import com.sportshub.dto.role.RoleUpdateDto;
import com.sportshub.entity.role.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleEntity toEntity(RoleCreateDto dto);
    RoleEntity toEntity(RoleUpdateDto dto);
    RoleDto toDto(RoleEntity entity);
    List<RoleDto> toDtoList(List<RoleEntity> entities);
}
