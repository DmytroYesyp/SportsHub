package com.sportshub.repository.user;
import com.sportshub.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    @Query(
            value = "SELECT * FROM users u WHERE u.reset_password_token IS NOT NULL ",
            nativeQuery = true)
    User findByResetPasswordToken(String resetToken);

}