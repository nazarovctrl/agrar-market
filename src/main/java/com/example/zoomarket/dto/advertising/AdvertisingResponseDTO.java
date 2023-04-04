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
    public static List<AdvertisingResponseDTO> of(List<AdvertisingEntity> advertisingEntities) {
        return advertisingEntities.stream().map((item ->
             new AdvertisingResponseDTO(
                    item.getId(),
                    item.getImage(),
                    item.getPage(),
                    item.isStatus(),
                    item.getCreatedAt(),
                    item.getEndedAt()
            )
        )).toList();
    }

    public static AdvertisingResponseDTO of(AdvertisingEntity advertising){
        return new AdvertisingResponseDTO(
                advertising.getId(),
                advertising.getImage(),
                advertising.getPage(),
                advertising.isStatus(),
                advertising.getCreatedAt(),
                advertising.getEndedAt()
        );
    }

}
