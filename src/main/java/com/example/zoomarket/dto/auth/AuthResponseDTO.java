package com.example.zoomarket.dto.auth;

import com.example.zoomarket.enums.ProfileRole;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private ProfileRole role;
    private String accessToken;

    private String refreshToken;
}
