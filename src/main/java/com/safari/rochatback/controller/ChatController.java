package com.safari.rochatback.controller;

import com.safari.rochatback.entity.ChatMessage;
import com.safari.rochatback.entity.ChatNotification;
import com.safari.rochatback.exception.ResourceNotFoundException;
import com.safari.rochatback.service.ChatMessageService;
import com.safari.rochatback.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public ChatMessage processMessage(@Payload ChatMessage chatMessage) {
        chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .ifPresentOrElse(chatMessage::setChatId, () -> new ResourceNotFoundException("Chat room not found"));

        ChatMessage saved = chatMessageService.save(chatMessage);

        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/notifications",
                new ChatNotification(saved.getId(), saved.getSenderId(), saved.getSenderName()));

        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages", saved);
        return saved;
    }
}
