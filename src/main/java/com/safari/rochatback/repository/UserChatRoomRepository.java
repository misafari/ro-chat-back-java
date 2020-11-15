package com.safari.rochatback.repository;

import com.safari.rochatback.entity.UserChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserChatRoomRepository extends MongoRepository<UserChatRoom, String> {
    Optional<UserChatRoom> findByChatIdAndUsername(String chatId, String username);
    List<UserChatRoom> findAllByUsername(String username);
}
