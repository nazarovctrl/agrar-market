package com.example.zoomarket.dto.email;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class PhoneHistoryResponseDTO {
    private Integer id;
    private String phone;
    private String message;
    private LocalDateTime createdDate;

}
