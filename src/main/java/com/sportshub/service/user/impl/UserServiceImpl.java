package com.sportshub.service.user.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.user.UserCreateDto;
import com.sportshub.dto.user.UserDto;
import com.sportshub.dto.user.UserUpdateDto;
import com.sportshub.entity.user.UserEntity;
import com.sportshub.exception.BadRequestException;
import com.sportshub.exception.ConflictException;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.user.UserMapper;
import com.sportshub.patch.UserPatch;
import com.sportshub.repository.user.UserRepository;
import com.sportshub.service.user.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
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

        try {
        userEntity = userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("users_email_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("User with email '%s' already exists!", userEntity.getEmail());
                    throw new ConflictException(errorMessage);
                }
            }
        }

        return userMapper.toDto(userEntity);
    }

    @Override
    public List<UserDto> findAll(int limit, Integer page) {
        List<UserEntity> userEntities = userRepository.findAllUsers(PageRequest.of(page - 1, limit));
        return userMapper.toDtoList(userEntities);
    }

    @Override
    public CountDto getCount() {
        int usersCount = (int) userRepository.count();
        return new CountDto(usersCount);
    }

    @Override
    public UserDto find(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));;
        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional
    public void update(Long id, UserUpdateDto userDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));

        userMapper.updateEntity(userEntity, userDto);

        userRepository.save(userEntity);
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
        int affectedRaws = userRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("User not found!");
        }
    }
}

