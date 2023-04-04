package com.example.zoomarket.entity;

import com.example.zoomarket.dto.advertising.AdvertisingResponseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class AdvertisingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;
    private short page; // qaysi bannerga qo'yilishi
    private boolean status;
    private Timestamp createdAt;
    private Timestamp endedAt;

    public AdvertisingEntity(String image, short page, boolean status, Timestamp createdAt, Timestamp endedAt) {
        this.image = image;
        this.page = page;
        this.status = status;
        this.createdAt = createdAt;
        this.endedAt = endedAt;
    }

    public static AdvertisingEntity from(AdvertisingResponseDTO advertisingResponseDTO){
        return AdvertisingEntity.builder()
                .id(advertisingResponseDTO.id())
                .image(advertisingResponseDTO.image())
                .page(advertisingResponseDTO.page())
                .status(advertisingResponseDTO.status())
                .createdAt(advertisingResponseDTO.createAt())
                .endedAt(advertisingResponseDTO.endedAt())
                .build();
    }

}
