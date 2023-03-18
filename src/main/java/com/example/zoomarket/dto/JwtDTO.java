package com.example.zoomarket.dto;

import com.example.zoomarket.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class JwtDTO {
    private Integer id;

    private String phone;
    private ProfileRole role;

    public JwtDTO(String phone, ProfileRole role) {
        this.phone = phone;
        this.role = role;
    }
}
