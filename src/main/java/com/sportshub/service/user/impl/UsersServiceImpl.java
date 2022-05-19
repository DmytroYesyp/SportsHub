package com.sportshub.service.user.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sportshub.entity.role.Roles;
import com.sportshub.entity.user.User;
import com.sportshub.repository.role.RoleRepository;
import com.sportshub.repository.user.UserRepository;
import com.sportshub.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class UsersServiceImpl implements UserDetailsService, com.sportshub.service.user.UsersService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public String getUsernameFromToken(HttpServletRequest request) {
        String key = SecurityConfig.key;
        String autorizationHeader = request.getHeader(AUTHORIZATION);
        String token = autorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(key.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();

    }

    @Override
    public String getUserLogo(HttpServletRequest request) {
        if (userRepository.findUserByEmail(getUsernameFromToken(request)).isEmpty())
            return null;

        return userRepository.findUserByEmail(getUsernameFromToken(request)).get().getLogo_url();
    }


    @Override
    public User uploadImageToUserProfile(HttpServletRequest request) throws IOException {

        if (userRepository.findUserByEmail(getUsernameFromToken(request)).isEmpty())
            return null;

        User user = userRepository.findUserByEmail(getUsernameFromToken(request)).get();


        return user;
    }


    @Override
    public ResponseEntity<String> addNewUser(User user) {
        boolean userOptional = userRepository.findUserByEmail(user.getEmail()).isPresent();
        if (userOptional) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        user.setLogo_url("User.png");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.getById(1L));
        userRepository.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/users/registerUser").toUriString());
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<String> setRole(Long userId, Long roleId) {

        User user = userRepository.getById(userId);
        Roles role = roleRepository.getById(roleId);


        role.getUser().add(user);

        role.setUser(role.getUser());

        user.getRoles().add(role);

        user.setRoles(user.getRoles());


        roleRepository.save(role);
        userRepository.save(user);

        return ResponseEntity.ok("User id " + userId.toString() + " Role id " + roleId.toString());
    }

    @Override
    public List<User> GetUsers() {
        return userRepository.findAll();
    }


    @Override
    public ResponseEntity<Collection<Roles>> getUserRole(long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userRepository.findById(userId).get().getRoles());
    }


    @Override
    public ResponseEntity<User> getUserById(long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userRepository.findById(userId).get());
    }

    @Override
    public ResponseEntity<Long> deleteUser(long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok(userId);
    }


    @Override
    public ResponseEntity<User> findUserByEmail(String email) {

        User user = userRepository.findUserByEmail(email).orElseThrow();

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity updateUser(long userId, User upd_user) {


        if (userRepository.findById(userId).isEmpty())
            return ResponseEntity.notFound().build();

        User user = userRepository.getById(userId);

        if (upd_user.getEmail() != null && upd_user.getEmail().length() > 0 && !Objects.equals(user.getEmail(), upd_user.getEmail())) {
            user.setEmail(upd_user.getEmail());
        }


        if (upd_user.getFirstName() != null && upd_user.getFirstName().length() > 0 && !Objects.equals(user.getFirstName(), upd_user.getFirstName())) {
            user.setFirstName(upd_user.getFirstName());
        }

        if (upd_user.getLastName() != null && upd_user.getLastName().length() > 0 && !Objects.equals(user.getLastName(), upd_user.getLastName())) {
            user.setLastName(upd_user.getLastName());
        }


        if (upd_user.getPassword() != null && upd_user.getPassword().length() > 8 && !Objects.equals(user.getPassword(), upd_user.getPassword())) {
            user.setPassword(upd_user.getPassword());
        }


        userRepository.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/registerUser").toUriString());
        return ResponseEntity.created(uri).body(null);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.get().getRoles().forEach(roles -> {
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);
    }


    @Override
    public void updateResetPasswordToken(String token, String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        User new_user = user.get();
        new_user.setResetPasswordToken(token);
        userRepository.save(new_user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(passwordEncoder.encode(newPassword));
//        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }


}
