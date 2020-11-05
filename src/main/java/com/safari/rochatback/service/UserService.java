package com.safari.rochatback.service;

import com.safari.rochatback.entity.Profile;
import com.safari.rochatback.repository.ProfileRepository;
import com.safari.rochatback.repository.UserRepository;
import com.safari.rochatback.entity.dto.UserRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final ProfileRepository profileRepository;

    public void save(UserRegister userRegister) {
        repository.save(userRegister.getUser());
        profileRepository.save(userRegister.getProfile());
    }

    public Profile findProfile(String username) throws Exception { // todo change exception
        return profileRepository.findByUsername(username).orElseThrow(Exception::new);
    }

    public List<Profile> findAllProfile() {
        return profileRepository.findAll();
    }
}
