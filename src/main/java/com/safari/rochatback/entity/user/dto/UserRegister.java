package com.safari.rochatback.entity.user.dto;

import com.safari.rochatback.entity.user.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserRegister {
    @NotBlank
    @Max(20)
    private String username;
    @NotBlank
    @Min(8)
    private String password;

    public User convert() {
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        return user;
    }
}
