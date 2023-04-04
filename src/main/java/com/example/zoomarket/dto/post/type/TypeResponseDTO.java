package com.example.zoomarket.dto.post.type;

import com.example.zoomarket.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeResponseDTO {
    private Long id;
    private String attachId;
    private String name;
    private Long categoryId;
}