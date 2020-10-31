package com.safari.rochatback.controller;

import com.safari.rochatback.entity.group.Group;
import com.safari.rochatback.entity.group.dto.GroupRegister;
import com.safari.rochatback.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(GroupController.ROOT_PATH)
@RequiredArgsConstructor
public class GroupController {
    public static final String ROOT_PATH = "group";
    private final GroupService service;

    @PostMapping
    public void save(GroupRegister groupRegister) {
        service.save(groupRegister);
    }

    @GetMapping
    public List<Group> getAll() {
        return service.findAll();
    }
}
