package com.sportshub.service.jwt;

import com.sportshub.dto.user.data.UserData;

public interface JwtService {
    String generateToken(UserData userData);
}
