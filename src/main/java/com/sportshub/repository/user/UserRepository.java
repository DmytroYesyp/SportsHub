package com.sportshub.repository.user;

import com.sportshub.entity.user.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CustomUserRepository, JpaRepository<UserEntity, Long> {
    @Query("FROM UserEntity")
    List<UserEntity> findAllUsers(Pageable pageable);

    UserEntity findByEmail(String email);

    @Modifying
    @Query("DELETE FROM UserEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
