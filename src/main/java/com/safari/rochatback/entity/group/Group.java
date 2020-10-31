package com.safari.rochatback.entity.group;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Group {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private LocalDateTime createTime;
}
