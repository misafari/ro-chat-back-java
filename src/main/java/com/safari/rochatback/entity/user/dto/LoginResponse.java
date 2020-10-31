package com.safari.rochatback.entity.user.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String username;
    private String token;
}
