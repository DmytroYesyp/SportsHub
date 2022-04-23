package com.sportshub.service.user.impl;

import com.sportshub.dto.user.UserCreateDto;
import com.sportshub.dto.user.UserDto;
import com.sportshub.dto.user.UserUpdateDto;
import com.sportshub.entity.user.UserEntity;
import com.sportshub.exception.BadRequestException;
import com.sportshub.exception.ConflictException;
import com.sportshub.mapper.user.UserMapper;
import com.sportshub.patch.UserPatch;
import com.sportshub.repository.user.UserRepository;
import com.sportshub.service.user.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto create(UserCreateDto userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);

        String passwordHash = bCryptPasswordEncoder.encode(userDto.getPassword());
        userEntity.setPasswordHash(passwordHash);

        userEntity = userRepository.create(userEntity);

        return userMapper.toDto(userEntity);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.toDtoList(userEntities);
    }

    @Override
    public UserDto find(Long id) {
        UserEntity userEntity = userRepository.find(id);
        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional
    public void update(Long id, UserUpdateDto userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);
        userRepository.update(id, userEntity);
    }
    @Override
    public void patch(Long id, UserPatch userPatch) {
        if (userPatch.isEmpty()) {
            throw new BadRequestException("User patch is empty!");
        }

        userRepository.patch(id, userPatch);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }
}

