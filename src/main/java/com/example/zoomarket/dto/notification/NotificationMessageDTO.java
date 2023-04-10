package com.example.zoomarket.dto.notification;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

@Data
public class NotificationMessageDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String body;
    private String image;
    private Map<String, String> data;
}
