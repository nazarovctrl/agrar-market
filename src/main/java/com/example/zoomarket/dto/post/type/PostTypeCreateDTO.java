package com.example.zoomarket.dto.post.type;

import com.example.zoomarket.enums.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTypeCreateDTO {
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotNull(message = "Type cannot be null")
    private Type type;
}
