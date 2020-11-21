package com.safari.rochatback.entity.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String firstName;
    private String surname;
    private String token;
}
