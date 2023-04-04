package com.example.zoomarket.dto.advertising;

import com.example.zoomarket.entity.AdvertisingEntity;

import java.sql.Timestamp;
import java.util.List;

public record AdvertisingResponseDTO(
        Integer id,
        String image,
        short page,
        boolean status,
        Timestamp createAt,
        Timestamp endedAt
){
    public static List<AdvertisingResponseDTO> from(List<AdvertisingEntity> advertisingEntities) {
        return advertisingEntities.stream().map((item -> {
            return new AdvertisingResponseDTO(
                    item.getId(),
                    item.getImage(),
                    item.getPage(),
                    item.isStatus(),
                    item.getCreatedAt(),
                    item.getEndedAt()
            );
        }
        )).toList();
    }
}
