package com.safari.rochatback.service;

import com.safari.rochatback.entity.ChatMessage;
import com.safari.rochatback.entity.ChatRoom;
import com.safari.rochatback.entity.MessageStatus;
import com.safari.rochatback.exception.ResourceNotFoundException;
import com.safari.rochatback.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
     private final ChatMessageRepository repository;
    private final MongoOperations mongoOperations;

    public CompletableFuture<ChatMessage> save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return CompletableFuture.completedFuture(chatMessage);
    }

    public long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED);
    }

//    public List<ChatMessage> findChatMessages(String chatId) {
//        var chatId = chatRoomService.getChatId(chatId);
//
//        var messages = chatId.map(repository::findByChatId).orElse(new ArrayList<>());
//
//        if(!messages.isEmpty()) {
//            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
//        }
//
//        return messages;
//    }

    public ChatMessage findById(String id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }

    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        Query query = new Query(
                Criteria
                        .where("senderId").is(senderId)
                        .and("recipientId").is(recipientId));
        Update update = Update.update("status", status);
        mongoOperations.updateMulti(query, update, ChatMessage.class);
    }

    public List<ChatMessage> findAllByUsername(String username) {
        return repository.findAllByRecipientId(username);
    }

    public List<ChatMessage> findAllByUsername(String username, Long timestamp) {
        return repository.findAllByRecipientIdAndTimestampIsAfter(username, timestamp);
    }
}
