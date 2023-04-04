package com.example.zoomarket.dto.post.category;

import com.example.zoomarket.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private Type type;
}
