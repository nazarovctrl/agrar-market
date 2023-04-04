package com.example.zoomarket.controller;

import com.example.zoomarket.dto.advertising.AdvertisingRequestDTO;
import com.example.zoomarket.dto.advertising.AdvertisingResponseDTO;
import com.example.zoomarket.service.AdvertisingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Tag(name = "Advertising Controller", description = "This Controller for advetising")
@RestController
@RequestMapping("/advertising")
@RequiredArgsConstructor
public class AdvertisingController {
    private final AdvertisingService advertisingService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for get all advertisements", description = "This method used to get all advertisements")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get")
    public ResponseEntity<Page<AdvertisingResponseDTO>> getList(
            @RequestParam Integer page,
            @RequestParam Integer size
    ){
        Page<AdvertisingResponseDTO> getAdvertisingList = advertisingService.getAll(page, size);
        return ResponseEntity.ok(getAdvertisingList);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for get advertising by id", description = "This method used to get advertising by id")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<AdvertisingResponseDTO> get(@PathVariable Integer id) {
        AdvertisingResponseDTO advertisingResponseDTO = advertisingService.get(id);
        return ResponseEntity.ok(advertisingResponseDTO);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for delete advertising by id", description = "This method used to delete advertising by id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        advertisingService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for create advertising", description = "This method used to create advertising")
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody AdvertisingRequestDTO advertisingRequestDTO) {
        MultipartFile multipartFile = null;
        advertisingService.create(advertisingRequestDTO, multipartFile);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
