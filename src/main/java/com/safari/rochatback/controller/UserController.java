package com.safari.rochatback.controller;

import com.safari.rochatback.entity.user.dto.UserRegister;
import com.safari.rochatback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.ROOT_PATH)
@RequiredArgsConstructor
public class UserController {
    public static final String ROOT_PATH = "user";
    private final UserService service;

    @PostMapping
    public void save(UserRegister userRegister) {
        service.save(userRegister);
    }
}
