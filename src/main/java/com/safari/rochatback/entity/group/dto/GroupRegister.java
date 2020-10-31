package com.safari.rochatback.entity.group.dto;

import com.safari.rochatback.entity.group.Group;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class GroupRegister {
    @NotBlank
    private String name;
    private String description;

    public Group convert() {
        Group group = new Group();

        group.setName(this.name);
        group.setDescription(this.description);
        group.setCreateTime(LocalDateTime.now());

        return group;
    }
}
