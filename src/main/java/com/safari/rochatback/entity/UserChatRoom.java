package com.safari.rochatback.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@NoArgsConstructor
public class UserChatRoom {
    @Id
    private String id;
    private String username;
    private String chatId;

    public UserChatRoom(String username, String chatId) {
        this.username = username;
        this.chatId = chatId;
    }
}
