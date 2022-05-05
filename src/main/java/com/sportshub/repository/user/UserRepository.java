package com.sportshub.repository.user;
import com.sportshub.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByEmail(String email);

    @Query(
            value = "SELECT * FROM users u WHERE u.reset_password_token IS NOT NULL ",
            nativeQuery = true)
    Users findByResetPasswordToken(String resetToken);
}

