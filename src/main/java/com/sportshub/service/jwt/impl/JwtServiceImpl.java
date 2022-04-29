package com.sportshub.service.jwt.impl;

import com.sportshub.dto.user.credentials.UserCredentials;
import com.sportshub.dto.user.data.UserData;
import com.sportshub.entity.user.UserEntity;
import com.sportshub.exception.UnauthenticatedException;
import com.sportshub.repository.permission.PermissionRepository;
import com.sportshub.repository.user.UserRepository;
import com.sportshub.service.auth.impl.AuthServiceImpl;
import com.sportshub.service.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.sportshub.constants.Constants.AUTH_ISSUER;
import static com.sportshub.constants.Constants.USER_DATA_CLAIMS;

@Service
public class JwtServiceImpl implements JwtService {
    private final long authTokenLifetime;
    private final String jwtSigningKey;

    public JwtServiceImpl(HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse,
                          UserRepository userRepository,
                          PermissionRepository permissionRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          @Value("${AUTH_TOKEN_LIFETIME}") long authTokenLifetime,
                          @Value("${JWT_SIGNING_KEY}") String jwtSigningKey) {

        this.authTokenLifetime = 1000 * authTokenLifetime;
        this.jwtSigningKey = jwtSigningKey;
    }

    @Override
    public String generateToken(UserData userData) {
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
