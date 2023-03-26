package com.example.zoomarket.controller;


import com.example.zoomarket.dto.auth.AuthResponseDTO;
import com.example.zoomarket.dto.auth.VerificationDTO;
import com.example.zoomarket.dto.profile.ProfileResponseDTO;
import com.example.zoomarket.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "Authorization Controller", description = "This controller for authorization")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }


    @Operation(summary = "Method for registration", description = "This method used to create a user")
    @PostMapping("/authorization/{phone}")
    private ResponseEntity<ProfileResponseDTO> registration(@PathVariable("phone") String phone) {
        log.info("Registration : phone {}", phone);

        ProfileResponseDTO result = service.registration(phone);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Method fro verification", description = "This method used to verifying by phone after registration")
    @PutMapping("/verification")
    private ResponseEntity<AuthResponseDTO> verification(@Valid @RequestBody VerificationDTO verificationDTO) {
        log.info("Verification:dto {}", verificationDTO.toString());

        AuthResponseDTO result = service.verification(verificationDTO);

        return ResponseEntity.ok(result);
    }


}
