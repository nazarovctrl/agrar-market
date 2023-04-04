package com.example.zoomarket.controller;

import com.example.zoomarket.dto.advertising.AdvertisingResponseDTO;
import com.example.zoomarket.service.AdvertisingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Advertising Controller", description = "This Controller for advetising")
@RestController
@RequestMapping("/advertising")
@RequiredArgsConstructor
public class AdvertisingController {
    private final AdvertisingService advertisingService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for get all post", description = "This method used to get all posts")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/list")
    public ResponseEntity<Page<AdvertisingResponseDTO>> getList(
            @RequestParam Integer page,
            @RequestParam Integer size
    ){
        Page<AdvertisingResponseDTO> getAdvertisingList = advertisingService.getAll(page, size);
        return ResponseEntity.ok(getAdvertisingList);
    }

    

}
