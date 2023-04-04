package com.example.zoomarket.controller;

import com.example.zoomarket.dto.post.category.CategoryCreateDTO;
import com.example.zoomarket.dto.post.category.CategoryResponseDTO;
import com.example.zoomarket.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "Category Controller", description = "This Controller for post type")
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for create post type", description = "This method used to create post type")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryCreateDTO postCategoryCreateDTO) {
        CategoryResponseDTO result = categoryService.create(postCategoryCreateDTO);
        return ResponseEntity.ok(result);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for get animal post types", description = "This method used to get animal post types")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/animal")
    public ResponseEntity<List<CategoryResponseDTO>> getAnimals() {
        List<CategoryResponseDTO> result = categoryService.getAnimalTypes();
        return ResponseEntity.ok(result);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for get plants post types", description = "This method used to get plant post types")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/plant")
    public ResponseEntity<List<CategoryResponseDTO>> getPlants() {
        List<CategoryResponseDTO> result = categoryService.getPlantTypes();
        return ResponseEntity.ok(result);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        com.example.zoomarket.config.security.CustomUserDetails user = (com.example.zoomarket.config.security.CustomUserDetails) authentication.getPrincipal();

        return user.getId();
    }
}