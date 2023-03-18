package com.example.zoomarket.dto.profile;

import com.example.zoomarket.dto.attach.PreviewAttachDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileInfoDTO {
    private Integer id;
    private String name;
    private String surname;
    private PreviewAttachDTO photo;

}
