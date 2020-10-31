package com.safari.rochatback.controller;

import com.safari.rochatback.entity.user.dto.LoginRequest;
import com.safari.rochatback.entity.user.dto.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = AuthController.ROOT_PATH)
public class AuthController {
    public static final String ROOT_PATH = "auth";

    @PostMapping("login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setUsername(request.getUsername());
        loginResponse.setToken("dawihdoawhdfouabfiawdaowu4128037423hflasebnfsebawf2"); // todo generate

        return loginResponse;
    }
}
