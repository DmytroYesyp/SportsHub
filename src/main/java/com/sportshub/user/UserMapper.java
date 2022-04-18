package com.sportshub.user;

import com.sportshub.dto.user.UserCreateDto;
import com.sportshub.dto.user.UserDto;
import com.sportshub.entity.user.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserCreateDto userDto);

    UserDto toDto(UserEntity userEntity);

    List<UserDto> toDtoList(List<UserEntity> userEntities);
}
