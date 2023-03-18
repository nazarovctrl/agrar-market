package com.example.zoomarket.controller;

import com.example.zoomarket.dto.post.type.PostTypeCreateDTO;
import com.example.zoomarket.dto.post.type.PostTypeResponseDTO;
import com.example.zoomarket.service.PostTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Post Type Controller", description = "This Controller for post type")
@RestController
@RequestMapping("/post/type")
public class PostTypeController {
    private final PostTypeService postTypeService;

    public PostTypeController(PostTypeService postTypeService) {
        this.postTypeService = postTypeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<PostTypeResponseDTO> create(@Valid @RequestBody PostTypeCreateDTO postTypeCreateDTO) {
        PostTypeResponseDTO result = postTypeService.create(postTypeCreateDTO);
        return ResponseEntity.ok(result);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        com.example.zoomarket.config.security.CustomUserDetails user = (com.example.zoomarket.config.security.CustomUserDetails) authentication.getPrincipal();

        return user.getId();
    }
}