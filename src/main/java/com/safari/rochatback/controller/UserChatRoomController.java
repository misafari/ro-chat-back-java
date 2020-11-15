package com.safari.rochatback.controller;

import com.safari.rochatback.entity.UserChatRoom;
import com.safari.rochatback.service.UserChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UserChatRoomController.ROOT_PATH)
@RequiredArgsConstructor
public class UserChatRoomController {
    public static final String ROOT_PATH = "user-chat-room";
    private final UserChatRoomService service;

    @GetMapping("byUsername/{username}")
    public List<UserChatRoom> getAllByUsername(@PathVariable String username) {
        return service.findAllByUsername(username);
    }
}
