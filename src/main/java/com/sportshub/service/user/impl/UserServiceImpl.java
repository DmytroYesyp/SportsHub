package com.sportshub.service.user.impl;

import com.sportshub.dto.user.UserCreateDto;
import com.sportshub.dto.user.UserDto;
import com.sportshub.dto.user.UserUpdateDto;
import com.sportshub.entity.user.UserEntity;
import com.sportshub.exception.ConflictException;
import com.sportshub.repository.user.UserRepository;
import com.sportshub.service.user.UserService;
import com.sportshub.user.UserMapper;
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
//        UserEntity userEntity = userMapper.toEntity(userDto);
//        try {
//            int affectedRaws = userRepository.update(id, userEntity);
//            if (affectedRaws == 0) {
//                new NotFoundException("User not found!");
//            }
//        } catch (DataIntegrityViolationException e) {
//            Throwable cause = e.getCause();
//            if (cause instanceof ConstraintViolationException) {
//                if ("users_username_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
//                    String errorMessage = String.format("User with username '%s' already exists!", userEntity.getUsername());
//                    throw new ConflictException(errorMessage);
//                }
//            }
//
//            throw e;
//        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }
}

