package com.sportshub.repository.user;

import com.sportshub.entity.user.UserEntity;
import com.sportshub.patch.UserPatch;

public interface CustomUserRepository {
    void patch(Long id, UserPatch userPatch);
}

