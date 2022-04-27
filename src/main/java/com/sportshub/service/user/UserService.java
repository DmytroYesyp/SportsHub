package com.sportshub.service.user;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.user.UserCreateDto;
import com.sportshub.dto.user.UserDto;
import com.sportshub.dto.user.UserUpdateDto;
import com.sportshub.patch.UserPatch;

import java.util.List;

public interface UserService {
    UserDto create(UserCreateDto userDto);
    List<UserDto> findAll(int limit, Integer page);
    CountDto getCount();
    UserDto find(Long id);
    void update(Long id, UserUpdateDto userDto);
    void patch(Long id, UserPatch userPatch);
    void delete(Long id);
}

