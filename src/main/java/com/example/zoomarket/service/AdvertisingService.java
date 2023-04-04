package com.example.zoomarket.service;

import com.example.zoomarket.dto.advertising.AdvertisingRequestDTO;
import com.example.zoomarket.dto.advertising.AdvertisingResponseDTO;
import com.example.zoomarket.entity.AdvertisingEntity;
import com.example.zoomarket.exp.advertising.AdvertisingNotFoundException;
import com.example.zoomarket.repository.AdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import org.slf4j.Logger;

@Service
@RequiredArgsConstructor
public class AdvertisingService {
    private final AdvertisingRepository advertisingRepository;
//    private static final Logger logger = LoggerFactory.getLogger(AdvertisingService.class);

    public Page<AdvertisingResponseDTO> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AdvertisingEntity> getObj = advertisingRepository.findAll(pageable);
        return new PageImpl<>(AdvertisingResponseDTO.of(getObj.getContent()), pageable, getObj.getTotalElements());
    }

    public AdvertisingResponseDTO get(Integer id){
        AdvertisingEntity advertisingEntity = advertisingRepository.findById(id).orElseThrow(() -> new AdvertisingNotFoundException(
                MessageFormat.format("{0} advertising not found", id))
        );

        return AdvertisingResponseDTO.of(advertisingEntity);
    }

    public void delete(Integer id){
        AdvertisingResponseDTO advertisingResponseDTO = get(id);
        advertisingRepository.delete(AdvertisingEntity.from(advertisingResponseDTO));
    }

    public void create(AdvertisingRequestDTO advertisingRequestDTO, MultipartFile multipartFile){
        String imageName = multipartFile.getOriginalFilename();
//        saveImage(multipartFile, imageName);
        advertisingRepository.save(AdvertisingRequestDTO.of(advertisingRequestDTO, imageName));
    }

    public void saveImage(MultipartFile multipartFile, String imageName) {
        try {
            Path imagePath = Paths.get("images", imageName);
            Files.createDirectories(imagePath.getParent());
            Files.write(imagePath, multipartFile.getBytes());
        } catch (IOException e){
           System.out.print("Image was not saved");
        }
    }



}
