package com.safari.rochatback.repository;

import com.safari.rochatback.entity.ChatMessage;
import com.safari.rochatback.entity.MessageStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);
    List<ChatMessage> findByChatId(String chatId);
    List<ChatMessage> findAllByRecipientId(String username);
    List<ChatMessage> findAllByRecipientIdAndTimestampIsAfter(String username, Long timestamp);
    List<ChatMessage> findAllByChatId(String chatId);
    List<ChatMessage> findAllByChatIdAndTimestampAfter(String chatId, Long timestamp);
    List<ChatMessage> findAllByChatIdInAndTimestampAfter(List<String> chatIds, Long timestamp);
}
