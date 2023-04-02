package com.example.zoomarket.dto.post.type;

import com.example.zoomarket.enums.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeCreateDTO {
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "attachId cannot be blank")
    private String attachId;

    @Positive(message = "Invalid categoryId")
    private Long categoryId;
}
