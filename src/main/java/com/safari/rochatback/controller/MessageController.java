package com.safari.rochatback.controller;

import com.safari.rochatback.entity.ChatMessage;
import com.safari.rochatback.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(MessageController.ROOT_PATH)
@RequiredArgsConstructor
public class MessageController {
    public static final String ROOT_PATH = "message";
    private final ChatMessageService service;

    @GetMapping("getByChatId/{chatId}")
    public List<ChatMessage> getAllByChatId(@PathVariable String chatId) {
        return service.findAllByChatId(chatId);
    }

    @GetMapping("getByChatId/{chatId}/{timestamp}")
    public List<ChatMessage> getAllByChatIdAndTimestampAfter(@PathVariable String chatId, @PathVariable Long timestamp) {
        return service.findAllByChatIdAndTimestampAfter(chatId, timestamp);
    }

    @GetMapping("/byUsername/{username}")
    public List<ChatMessage> getAll(@PathVariable String username) {
        return service.findAllByUsername(username);
    }

    @GetMapping("/byUsername/{username}/{timestamp}")
    public List<ChatMessage> getAll(@PathVariable String username, @PathVariable Long timestamp) {
        return service.findAllByUsername(username, timestamp);
    }
}
