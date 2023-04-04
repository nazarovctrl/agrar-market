package com.example.zoomarket.dto.post;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponseDTO {
    private Long id;
    private Long typeId;
    private String title;
    private Double price;
    private String phone;
    private String location;
    private String description;

    private List<String> attachId;
    private Long profileId;
    private Long likeCount;
    private Boolean isLiked;
}
