package com.example.zoomarket.dto.profile;

import com.example.zoomarket.enums.ProfileRole;
import com.example.zoomarket.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponseDTO {

    private Integer id;
    private String fullName;


    private String phone;
    private ProfileStatus status;
    private ProfileRole role;
    private Boolean visible;

    private String photoId;
    private LocalDateTime createdDate;


}
