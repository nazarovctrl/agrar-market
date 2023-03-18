package com.example.zoomarket.controller;

import com.example.zoomarket.service.PostLikeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Post Type Controller", description = "This Controller for post type")
@RestController
@RequestMapping("/post/like")
public class PostLikeController {

    private final PostLikeService postLikeService;

    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create/{postId}")
    public ResponseEntity<Boolean> create(@PathVariable Long postId) {
        Boolean result = postLikeService.create(getUserId(), postId);
        return ResponseEntity.ok(result);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        com.example.zoomarket.config.security.CustomUserDetails user = (com.example.zoomarket.config.security.CustomUserDetails) authentication.getPrincipal();

        return user.getId();
    }
}
