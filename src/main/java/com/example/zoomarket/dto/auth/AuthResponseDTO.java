package com.example.zoomarket.dto.auth;

import com.example.zoomarket.enums.ProfileRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class AuthResponseDTO {
    private ProfileRole role;
    private String accessToken;

    private String refreshToken;
}
