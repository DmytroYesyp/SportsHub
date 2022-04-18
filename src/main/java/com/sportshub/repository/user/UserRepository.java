package com.sportshub.repository.user;

import com.sportshub.entity.user.UserEntity;
import com.sportshub.patch.UserPatch;

import java.util.List;

public interface UserRepository {
    UserEntity create(UserEntity UserEntity);
    List<UserEntity> findAll();
    UserEntity find(Long id);
    void update(UserEntity entity);
    void patch(Long id, UserPatch UserPatch);
    void delete(Long id);
}

