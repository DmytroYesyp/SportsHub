package com.sportshub.service.auth;

import com.sportshub.dto.auth.token.AuthTokenDto;
import com.sportshub.dto.user.credentials.UserCredentials;

public interface AuthService {
    AuthTokenDto login(UserCredentials userCredentials);
    AuthTokenDto getRefreshedToken();
}
