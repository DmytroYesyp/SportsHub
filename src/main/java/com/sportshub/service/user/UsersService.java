package com.sportshub.service.user;

import com.sportshub.entity.role.Roles;
import com.sportshub.entity.user.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface UsersService {
    String getUsernameFromToken(HttpServletRequest request);

    String getUserLogo(HttpServletRequest request);

    User uploadImageToUserProfile(HttpServletRequest request) throws IOException;

    ResponseEntity<String> addNewUser(User user);

    ResponseEntity<String> setRole(Long userId, Long roleId);

    List<User> GetUsers();

    ResponseEntity<Collection<Roles>> getUserRole(long userId);

    ResponseEntity<User> getUserById(long userId);

    ResponseEntity<Long> deleteUser(long userId);

    ResponseEntity<User> findUserByEmail(String email);

    ResponseEntity updateUser(long userId, User upd_user);

    void updateResetPasswordToken(String token, String email);

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);
}
