package com.sportshub.service.auth;

import com.sportshub.annotation.Auth;
import com.sportshub.dto.user.credentials.UserCredentials;

public interface AuthService {
    void login(UserCredentials userCredentials);
    void verifyAuthority(Auth auth);
}
