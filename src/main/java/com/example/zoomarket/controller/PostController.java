package com.example.zoomarket.controller;

import com.example.zoomarket.dto.post.PostCreateDTO;
import com.example.zoomarket.dto.post.PostResponseDTO;
import com.example.zoomarket.enums.Type;
import com.example.zoomarket.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Post Controller", description = "This Controller for post")
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Method for create post", description = "This method used to create post")
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<PostResponseDTO> create(@Valid @RequestBody PostCreateDTO postCreateDTO) {
        PostResponseDTO result = postService.create(getUserId(), postCreateDTO);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Method for create post", description = "This method used to delete post")
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long postId) {
        Boolean result = postService.delete(getUserId(), postId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Method for get all post", description = "This method used to get all posts")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/all")
    public ResponseEntity<Page<PostResponseDTO>> getAll(@RequestParam("page") Integer page,
                                                        @RequestParam("size") Integer size) {
        Page<PostResponseDTO> result = postService.getAll(page, size, getUserId());
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Method for get all animals", description = "This method used to get all animals")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/animals")
    public ResponseEntity<Page<PostResponseDTO>> getAnimals(@RequestParam("page") Integer page,
                                                        @RequestParam("size") Integer size) {
        Page<PostResponseDTO> result = postService.getAllByType(page, size, getUserId(), Type.ANIMAL);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Method for get all animals", description = "This method used to get animals which belongs to that user")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/profile/animals")
    public ResponseEntity<Page<PostResponseDTO>> getProfileAnimals(@RequestParam("page") Integer page,
                                                            @RequestParam("size") Integer size) {
        Page<PostResponseDTO> result = postService.getProfilePostsByType(page, size, getUserId(), Type.ANIMAL);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Method for get all plants", description = "This method used to get all plants")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/plants")
    public ResponseEntity<Page<PostResponseDTO>> getPlants(@RequestParam("page") Integer page,
                                                               @RequestParam("size") Integer size) {
        Page<PostResponseDTO> result = postService.getAllByType(page, size, getUserId(), Type.PLANT);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Method for get all plants", description = "This method used to get plants which belongs to that user")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/profile/plants")
    public ResponseEntity<Page<PostResponseDTO>> getProfilePlants(@RequestParam("page") Integer page,
                                                           @RequestParam("size") Integer size) {
        Page<PostResponseDTO> result = postService.getProfilePostsByType(page, size, getUserId(), Type.PLANT);
        return ResponseEntity.ok(result);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        com.example.zoomarket.config.security.CustomUserDetails user = (com.example.zoomarket.config.security.CustomUserDetails) authentication.getPrincipal();

        return user.getId();
    }
}
