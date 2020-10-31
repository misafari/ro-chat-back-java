package com.safari.rochatback.service;

import com.safari.rochatback.entity.user.Profile;
import com.safari.rochatback.entity.user.ProfileRepository;
import com.safari.rochatback.entity.user.UserRepository;
import com.safari.rochatback.entity.user.dto.UserRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final ProfileRepository profileRepository;

    public void save(UserRegister userRegister) {
        repository.save(userRegister.getUser());
        profileRepository.save(userRegister.getProfile());
    }

    public Profile findProfile(String username) throws Exception {
        return profileRepository.findByUsername(username)
                .orElseThrow(Exception::new);
    }
}
