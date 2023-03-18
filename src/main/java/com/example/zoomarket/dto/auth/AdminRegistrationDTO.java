package com.example.zoomarket.dto.auth;


import com.example.zoomarket.enums.ProfileRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdminRegistrationDTO {

    @NotBlank(message = "Full name required")
    private String fullName;

    @Email(message = "Email required")
    private String email;

    @NotBlank(message = "Role required")
    private ProfileRole role;

}
