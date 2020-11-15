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

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
     private final ChatMessageRepository repository;
    private final MongoOperations mongoOperations;

    public CompletableFuture<ChatMessage> save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessage.setTimestamp(new Date().getTime());
        repository.save(chatMessage);
        return CompletableFuture.completedFuture(chatMessage);
    }

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

    public List<ChatMessage> findAllByChatId(String chatId) {
        return repository.findAllByChatId(chatId);
    }

    public List<ChatMessage> findAllByChatIdAndTimestampAfter(String chatId, Long timestamp) {
        return repository.findAllByChatIdAndTimestampAfter(chatId, timestamp);
    }
}
