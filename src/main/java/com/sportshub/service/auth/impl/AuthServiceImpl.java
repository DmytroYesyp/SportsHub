package com.sportshub.service.auth.impl;

import com.sportshub.dto.auth.token.AuthTokenDto;
import com.sportshub.dto.user.credentials.UserCredentials;
import com.sportshub.dto.user.data.UserData;
import com.sportshub.entity.user.UserEntity;
import com.sportshub.exception.UnauthenticatedException;
import com.sportshub.repository.role.RoleRepository;
import com.sportshub.repository.user.UserRepository;
import com.sportshub.service.auth.AuthService;
import com.sportshub.service.jwt.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.sportshub.constants.Constants.AUTH_TOKEN_HEADER;

@Service
public class AuthServiceImpl implements AuthService {
    private final HttpServletResponse httpServletResponse;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(HttpServletResponse httpServletResponse,
                           JwtService jwtService,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.httpServletResponse = httpServletResponse;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public AuthTokenDto login(UserCredentials userCredentials) {
        String authToken = generateToken(userCredentials);

        httpServletResponse.setHeader(AUTH_TOKEN_HEADER, authToken);

        return new AuthTokenDto(authToken);
    }

    public AuthTokenDto getRefreshedToken() {
        String authToken =  httpServletResponse.getHeader(AUTH_TOKEN_HEADER);

        return new AuthTokenDto(authToken);
    }

    private String generateToken(UserCredentials userCredentials) {
        String email = userCredentials.getEmail();

        UserEntity userAuthData = userRepository.findByEmail(email);
        if (userAuthData == null) {
            throw new UnauthenticatedException("Invalid email!");
        }

        String password = userCredentials.getPassword();
        String passwordHash = userAuthData.getPasswordHash();
        if (!bCryptPasswordEncoder.matches(password, passwordHash)) {
            throw new UnauthenticatedException("Invalid password!");
        }

        Long userId = userAuthData.getId();

        List<String> roles = roleRepository.findUserRoleNames(userId);
        UserData userData = new UserData(userId, roles);

        return jwtService.generateToken(userData);
    }
}
