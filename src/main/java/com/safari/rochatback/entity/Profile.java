package com.safari.rochatback.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Profile {
    @Id
    private ObjectId id;
    private String username;
    private String firstName;
    private String surname;
    private String avatar;
    private String avatarBackGroundColor;
}
