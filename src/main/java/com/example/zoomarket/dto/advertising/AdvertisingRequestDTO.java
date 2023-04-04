package com.example.zoomarket.dto.advertising;

import com.example.zoomarket.entity.AdvertisingEntity;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record AdvertisingRequestDTO(
        @NotNull(message = "page cannot be null")
        short page,
        boolean status,
        Timestamp endedAt
)
{
    public static AdvertisingEntity of(AdvertisingRequestDTO advertisingRequestDTO, String image){
        return new AdvertisingEntity(
                image,
                advertisingRequestDTO.page(),
                advertisingRequestDTO.status(),
                new Timestamp(System.currentTimeMillis()),
                advertisingRequestDTO.endedAt()
        );
    }
}