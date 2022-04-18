package com.sportshub.service.user;

import com.sportshub.dto.user.UserCreateDto;
import com.sportshub.dto.user.UserDto;
import com.sportshub.dto.user.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserDto create(UserCreateDto userDto);
    List<UserDto> findAll();
    UserDto find(Long id);
    void update(Long id, UserUpdateDto userDto);
    void delete(Long id);
}

