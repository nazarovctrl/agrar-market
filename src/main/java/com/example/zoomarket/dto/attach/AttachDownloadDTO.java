package com.example.zoomarket.dto.attach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
@Setter
@AllArgsConstructor
public class AttachDownloadDTO {
    private Resource resource;
    private String contentType;

}
