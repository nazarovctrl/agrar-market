package com.example.zoomarket.dto.post;

import com.example.zoomarket.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponseDTO {
    private Long id;
    private Long typeId;
    private String title;
    private Double price;
    private Currency currency;
    private String phone;
    private String location;
    private String description;

    private List<String> attachLinks;
    private Long profileId;
    private Long likeCount;
    private Boolean isLiked;

    private LocalDateTime createdDate;
}
