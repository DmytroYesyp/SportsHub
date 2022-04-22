package com.sportshub.mapper.user;

import com.sportshub.dto.user.UserCreateDto;
import com.sportshub.dto.user.UserDto;
import com.sportshub.dto.user.UserUpdateDto;
import com.sportshub.entity.user.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserCreateDto userDto);
    UserEntity toEntity(UserUpdateDto userDto);
    UserDto toDto(UserEntity userEntity);
    List<UserDto> toDtoList(List<UserEntity> userEntities);
}
