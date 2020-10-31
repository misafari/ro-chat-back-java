package com.safari.rochatback.service;

import com.safari.rochatback.entity.group.Group;
import com.safari.rochatback.entity.group.GroupRepository;
import com.safari.rochatback.entity.group.dto.GroupRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository repository;

    public void save(GroupRegister register) {
        repository.save(register.convert());
    }

    public List<Group> findAll() {
        return repository.findAll();
    }
}
