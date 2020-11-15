package com.safari.rochatback.service;

import com.safari.rochatback.entity.ChatRoom;
import com.safari.rochatback.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository repository;

    public CompletableFuture<Optional<String>> getChatId(String chatId) {
        return CompletableFuture.completedFuture(repository
                .findByChatId(chatId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    ChatRoom chatRoom = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .build();

                    repository.save(chatRoom);

                    return Optional.of(chatId);
                }));
    }
}