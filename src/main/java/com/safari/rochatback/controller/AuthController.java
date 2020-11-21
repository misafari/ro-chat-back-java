package com.safari.rochatback.controller;

import com.safari.rochatback.entity.Profile;
import com.safari.rochatback.entity.dto.LoginRequest;
import com.safari.rochatback.entity.dto.LoginResponse;
import com.safari.rochatback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = AuthController.ROOT_PATH)
@RequiredArgsConstructor
public class AuthController {
    public static final String ROOT_PATH = "auth";
    private final UserService userService;

    @PostMapping("login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) throws Exception {
        LoginResponse loginResponse = new LoginResponse();

        Profile profile = userService.findProfile(request.getUsername());

        loginResponse.setFirstName(profile.getFirstName());
        loginResponse.setSurname(profile.getSurname());

        loginResponse.setToken("dawihdoawhdfouabfiawdaowu4128037423hflasebnfsebawf2"); // todo generate

        return loginResponse;
    }
}
