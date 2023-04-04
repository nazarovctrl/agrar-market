package com.example.zoomarket.controller;

import com.example.zoomarket.dto.post.type.TypeResponseDTO;
import com.example.zoomarket.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "Type Controller", description = "This Controller for post type")
@RestController
@RequestMapping("/type")
@AllArgsConstructor
public class TypeController {
    private final TypeService typeService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for Types by categoryId", description = "This method used to get types by categoryId")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/{categoryId}")
    public ResponseEntity<List<TypeResponseDTO>> getByCategoryId(@PathVariable Long categoryId) {
        List<TypeResponseDTO> result = typeService.getByCategoryId(categoryId);
        return ResponseEntity.ok(result);
    }

}