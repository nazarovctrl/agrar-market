package com.example.zoomarket.service;

import com.example.zoomarket.dto.advertising.AdvertisingResponseDTO;
import com.example.zoomarket.dto.post.PostResponseDTO;
import com.example.zoomarket.entity.AdvertisingEntity;
import com.example.zoomarket.repository.AdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisingService {
    private final AdvertisingRepository advertisingRepository;

    public Page<AdvertisingResponseDTO> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AdvertisingEntity> getObj = advertisingRepository.findAll(pageable);
        return new PageImpl<>(AdvertisingResponseDTO.from(getObj.getContent()), pageable, getObj.getTotalElements());
    }



}
