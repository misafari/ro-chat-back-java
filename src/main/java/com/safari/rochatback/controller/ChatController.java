package com.safari.rochatback.controller;

import com.safari.rochatback.entity.ChatMessage;
import com.safari.rochatback.service.ChatMessageService;
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

    @MessageMapping("/chat")
    public void processMessage(ChatMessage chatMessage) {
//        chatRoomService.getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
//                .ifPresentOrElse(chatMessage::setChatId, () -> new ResourceNotFoundException("Chat room not found"));
        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/msg", saved);
    }
}
