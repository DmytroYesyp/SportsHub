package com.sportshub.service.auth.impl;


import com.sportshub.annotation.Auth;
import com.sportshub.dto.user.credentials.UserCredentials;
import com.sportshub.entity.user.UserEntity;
import com.sportshub.exception.UnauthenticatedException;
import com.sportshub.exception.UnauthorizedException;
import com.sportshub.repository.permission.PermissionRepository;
import com.sportshub.repository.user.UserRepository;
import com.sportshub.service.auth.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String AUTH_TOKEN_HEADER = "Authorization";
    private static final String USER_DATA_CLAIMS = "user";
    private static final String AUTH_ISSUER = "SportsHub Auth";
    private static final String TOKEN_PREFIX = "Bearer";


    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final long authTokenLifetime;
    private final String jwtSigningKey;

    public AuthServiceImpl(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           UserRepository userRepository,
                           PermissionRepository permissionRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           @Value("${AUTH_TOKEN_LIFETIME}") long authTokenLifetime,
                           @Value("${JWT_SIGNING_KEY}") String jwtSigningKey) {

        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authTokenLifetime = 1000 * authTokenLifetime;
        this.jwtSigningKey = jwtSigningKey;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserData {
        private Long id;
    }


    public void login(UserCredentials userCredentials) {
        String authToken = generateToken(userCredentials);

        httpServletResponse.setHeader(AUTH_TOKEN_HEADER, authToken);
    }

    @Override
    public void verifyAuthority(Auth auth) {
        String authHeader = httpServletRequest.getHeader(AUTH_TOKEN_HEADER);
        if (authHeader == null) {
            throw new UnauthenticatedException("Missed Authorization header!");
        }

        String[] authHeaderParts = authHeader.trim().split("\\s+");
        if (authHeaderParts.length != 2 || !TOKEN_PREFIX.equals(authHeaderParts[0])) {
            throw new UnauthenticatedException("Invalid format of Auth header!");
        }

        String jwtToken = authHeaderParts[1];

        Map<String, Object> userData;
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(jwtToken).getBody();
            userData = claims.get(USER_DATA_CLAIMS, Map.class);
        } catch (RuntimeException e) {
            throw new UnauthenticatedException("Invalid JWT token!");
        }

        Long userId = (long) (Integer) userData.get("id");

        String permission = auth.permission();
        if (!permission.isBlank() && !permissionRepository.existPermissionForUser(permission, userId)) {
            throw new UnauthorizedException("No access rights to call this endpoint!");
        }

        String refreshedJwtToken = generateToken(new UserData(userId));

        httpServletResponse.setHeader(AUTH_TOKEN_HEADER, refreshedJwtToken);
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

        UserData userData = new UserData(userAuthData.getId());

        return generateToken(userData);
    }

    private String generateToken(UserData userData) {
        Claims claims = Jwts.claims();
        claims.put(USER_DATA_CLAIMS, userData);

        long creationTime = System.currentTimeMillis();
        long expirationTime = creationTime + authTokenLifetime;

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(AUTH_ISSUER)
                .setIssuedAt(new Date(creationTime))
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.HS256, jwtSigningKey)
                .compact();
    }
}
