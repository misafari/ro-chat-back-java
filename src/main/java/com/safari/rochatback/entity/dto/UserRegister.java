package com.safari.rochatback.entity.dto;

import com.safari.rochatback.entity.Profile;
import com.safari.rochatback.entity.User;
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
    private String firstName;
    private String surname;
    private String avatar;
    private String avatarBackGroundColor;

    public User getUser() {
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        return user;
    }

    public Profile getProfile() {
        Profile profile = new Profile();

        profile.setUsername(username);
        profile.setFirstName(firstName);
        profile.setSurname(surname);
        profile.setAvatar(avatar);
        profile.setAvatarBackGroundColor(avatarBackGroundColor);

        return profile;
    }
}
