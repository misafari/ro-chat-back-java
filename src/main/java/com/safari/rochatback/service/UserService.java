package com.safari.rochatback.service;

import com.safari.rochatback.entity.user.UserRepository;
import com.safari.rochatback.entity.user.dto.UserRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void save(UserRegister userRegister) {
        repository.save(userRegister.convert());
    }
}
