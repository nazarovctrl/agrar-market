package com.example.zoomarket.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationDTO {
    @Size(min = 12, message = "Phone required")
    private String phone;

    @Size(min = 4, max = 4, message = "Password required")
    private String code;

    @NotBlank(message = "Firebase token required")
    private String firebaseToken;
}
