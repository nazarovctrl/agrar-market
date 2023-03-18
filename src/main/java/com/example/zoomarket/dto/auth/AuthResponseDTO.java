package com.example.zoomarket.dto.auth;

import com.example.zoomarket.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthResponseDTO {
    private ProfileRole role;
    private String token;
}
