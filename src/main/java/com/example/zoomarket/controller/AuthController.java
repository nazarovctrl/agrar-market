package com.example.zoomarket.controller;


import com.example.zoomarket.dto.JwtDTO;
import com.example.zoomarket.dto.auth.AuthResponseDTO;
import com.example.zoomarket.dto.auth.VerificationDTO;
import com.example.zoomarket.dto.profile.ProfileResponseDTO;
import com.example.zoomarket.exp.auth.JWTTokenExpiredException;
import com.example.zoomarket.service.AuthService;
import com.example.zoomarket.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


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


    @Operation(summary = "Method for refresh token", description = "This method used for refresh token")
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader("AUTHORIZATION");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                JwtUtil.isTokenExpired(refreshToken);
                JwtDTO jwtDTO = JwtUtil.decodeRefreshToken(refreshToken);
                String accessToken = JwtUtil.encodeAccessToken(jwtDTO.getPhone(), jwtDTO.getRole());
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (JWTTokenExpiredException e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
                return;
            }


        }

    }

}
