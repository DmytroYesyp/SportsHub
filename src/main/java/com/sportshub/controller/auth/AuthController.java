package com.sportshub.controller.auth;

import com.sportshub.annotation.Auth;
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
    public void login(@RequestBody UserCredentials userCredentials) {
       authService.login(userCredentials);
    }

    @Auth
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    @PostMapping("refresh-token")
    public void refreshToken() {
    }
}
