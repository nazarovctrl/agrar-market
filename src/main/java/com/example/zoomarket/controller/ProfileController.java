package com.example.zoomarket.controller;

import com.example.zoomarket.config.security.CustomUserDetails;
import com.example.zoomarket.dto.profile.ProfileDetailDTO;
import com.example.zoomarket.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/profile")
@Tag(name = "Profile Controller", description = "This controller for authorization")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/detail")
    @Operation(summary = "Method for get detail", description = "This method used to get detail ")
    public ResponseEntity<ProfileDetailDTO> getDetail() {
        Long userId = getUserId();
        log.info("Profile get detail: user id {}", userId);
        ProfileDetailDTO result = profileService.getDetail(userId);
        return ResponseEntity.ok(result);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/full_name/{full_name}")
    @Operation(summary = "Method for edit full name", description = "This method used to edit full name ")
    public ResponseEntity<Boolean> editFullName(@PathVariable("full_name") String fullName) {
        Long userId = getUserId();
        log.info("Profile edit full name: user id {} , new full name {}", userId, fullName);
        Boolean result = profileService.editFullName(getUserId(), fullName);
        return ResponseEntity.ok(result);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/phone/{phone}")
    @Operation(summary = "Method for change phone", description = "This method used to change phone")
    public ResponseEntity<Boolean> changePhone(@PathVariable("phone") String phone) {
        Long userId = getUserId();
        log.info("Profile edit phone: user id {} , new phone {}", userId, phone);
        Boolean result = profileService.changePhone(userId, phone);
        return ResponseEntity.ok(result);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/email/{email}")
    @Operation(summary = "Method for change email", description = "This method used change email")
    public ResponseEntity<Boolean> changeEmail(@PathVariable("email") String email) {
        Long userId = getUserId();
        log.info("Profile edit full name: user id {} , new email {}", userId, email);
        Boolean result = profileService.changeEmail(userId, email);
        return ResponseEntity.ok(result);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/photo/{photo_id}")
    @Operation(summary = "Method for change photo", description = "This method used to change photo")
    public ResponseEntity<Boolean> changePhoto(@PathVariable("photo_id") String photoId) {
        Long userId = getUserId();
        log.info("Profile edit photo id: user id {} , new photo id {}", userId, photoId);
        Boolean result = profileService.changePhoto(userId, photoId);
        return ResponseEntity.ok(result);
    }


    public Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return user.getId();
    }
}
