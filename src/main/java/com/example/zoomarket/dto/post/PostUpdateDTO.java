package com.example.zoomarket.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostUpdateDTO {

    private Long typeId;

    private List<String> attachId;

    private String title;

    private Double price;

    private String phone;

    private String location;

    private String description;
}
