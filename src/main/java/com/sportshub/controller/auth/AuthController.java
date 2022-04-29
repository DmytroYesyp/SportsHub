package com.sportshub.controller.auth;

import com.sportshub.dto.auth.token.AuthTokenDto;
import com.sportshub.dto.user.credentials.UserCredentials;
import com.sportshub.service.auth.AuthService;
import io.swagger.annotations.ApiImplicitParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public AuthTokenDto login(@RequestBody UserCredentials userCredentials) {
       return authService.login(userCredentials);
    }

    @PostMapping("refresh-token")
    public AuthTokenDto refreshToken() {
        return authService.getRefreshedToken();
    }
}
