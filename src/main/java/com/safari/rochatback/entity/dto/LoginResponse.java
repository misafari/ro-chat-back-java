package com.safari.rochatback.entity.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String username;
    private String token;
}
