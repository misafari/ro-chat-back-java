package com.safari.rochatback.controller;

import com.safari.rochatback.entity.ChatMessage;
import com.safari.rochatback.service.ChatMessageService;
import com.safari.rochatback.service.ChatRoomService;
import com.safari.rochatback.service.UserChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final UserChatRoomService userChatRoomService;

    @MessageMapping("/chat")
    public void processMessage(ChatMessage chatMessage) {
        try {
            CompletableFuture<Optional<String>> chatRoom = chatRoomService.getChatId(chatMessage.getChatId());
            CompletableFuture<ChatMessage> saveMessage = chatMessageService.save(chatMessage);
            CompletableFuture<Optional<String>> addSenderChatRoom =
                    userChatRoomService.addUserChatRoom(chatMessage.getChatId(), chatMessage.getSenderName());

            CompletableFuture<Optional<String>> addRecipientChatRoom =
                    userChatRoomService.addUserChatRoom(chatMessage.getChatId(), chatMessage.getRecipientId());

            CompletableFuture.allOf(chatRoom, saveMessage, addSenderChatRoom, addRecipientChatRoom).join();

            messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/msg", saveMessage.get());
            messagingTemplate.convertAndSendToUser(chatMessage.getSenderId(), "/msg", saveMessage.get());
        } catch (InterruptedException e) {
            // todo do something
            e.printStackTrace();
        } catch (ExecutionException e) {
            // todo do something
            e.printStackTrace();
        }
    }
}
