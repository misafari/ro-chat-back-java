package com.safari.rochatback.entity.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequest {
    @NotNull
    private List<String> chatIds;
    @NotNull
    private Long timestamp;
}