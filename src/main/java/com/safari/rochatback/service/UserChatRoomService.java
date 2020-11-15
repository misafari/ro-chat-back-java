package com.safari.rochatback.service;

import com.safari.rochatback.entity.UserChatRoom;
import com.safari.rochatback.repository.UserChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class UserChatRoomService {
    private final UserChatRoomRepository repository;

    public CompletableFuture<Optional<String>> addUserChatRoom(String chatId, String username) {
        return CompletableFuture.completedFuture(
                repository.findByChatIdAndUsername(chatId, username)
                        .map(UserChatRoom::getId)
                        .or(() -> Optional.of(repository.save(new UserChatRoom(chatId, username)).getId())));
    }

    public List<UserChatRoom> findAllByUsername(String username) {
        return repository.findAllByUsername(username);
    }
}
