package com.example.zoomarket.dto.post.type;

import com.example.zoomarket.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTypeResponseDTO {
    private Long id;
    private String name;
    private Type type;
}
