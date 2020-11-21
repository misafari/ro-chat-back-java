package com.safari.rochatback.controller;

import com.safari.rochatback.service.UserChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserChatRoomController.ROOT_PATH)
@RequiredArgsConstructor
public class UserChatRoomController {
    public static final String ROOT_PATH = "user-chat-room";
    private final UserChatRoomService service;

    @GetMapping("byUsername/{username}")
    public List<String> getAllByUsername(@PathVariable String username) {
        return service.findAllByUsername(username);
    }

    @DeleteMapping("{username}/{chatId}")
    public void remove(@PathVariable String chatId, @PathVariable String username) {
        service.delete(username, chatId);
    }
}
