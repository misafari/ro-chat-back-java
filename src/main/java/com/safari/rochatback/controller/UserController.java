package com.safari.rochatback.controller;

import com.safari.rochatback.entity.Profile;
import com.safari.rochatback.entity.dto.UserRegister;
import com.safari.rochatback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.ROOT_PATH)
@RequiredArgsConstructor
public class UserController {
    public static final String ROOT_PATH = "user";
    private final UserService service;

    @PostMapping
    public String save(@RequestBody UserRegister userRegister) {
        service.save(userRegister);
        return "dawihdoawhdfouabfiawdaowu4128037423hflasebnfsebawf2"; // todo generate
    }

    @GetMapping("byUsername/{username}")
    public Profile getProfile(@PathVariable String username) throws Exception {
        return service.findProfile(username);
    }

    @GetMapping("profiles")
    public List<Profile> getProfile() {
        return service.findAllProfile();
    }
}
