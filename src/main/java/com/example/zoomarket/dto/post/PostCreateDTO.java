package com.example.zoomarket.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostCreateDTO {
    @Positive(message = "TypeId cannot be negative")
    private Long typeId;

    private List<String> attachIdList;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Positive(message = "Price cannot be negative")
    private Double price;

    @NotBlank(message = "Phone cannot be blank")
    private String phone;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotBlank(message = "Description cannot be blank")
    private String description;
}
