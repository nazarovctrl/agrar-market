package com.example.zoomarket.dto.auth;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @Size(min = 9, message = "Phone required")
    private String phone;

    @Size(min = 8, message = "Password required")
    private String password;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "phone='" + phone + '\'' +
                '}';
    }
}
