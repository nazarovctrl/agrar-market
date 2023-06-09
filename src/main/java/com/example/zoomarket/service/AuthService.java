package com.example.zoomarket.service;

import com.example.zoomarket.dto.auth.AuthResponseDTO;
import com.example.zoomarket.dto.auth.VerificationDTO;
import com.example.zoomarket.dto.profile.ProfileResponseDTO;
import com.example.zoomarket.entity.ProfileEntity;
import com.example.zoomarket.enums.ProfileRole;
import com.example.zoomarket.enums.ProfileStatus;
import com.example.zoomarket.exp.auth.*;
import com.example.zoomarket.exp.sms.LimitOutPutException;
import com.example.zoomarket.repository.ProfileRepository;
import com.example.zoomarket.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Slf4j
@Service
public class AuthService {
    private final ProfileRepository repository;
    private final SMSService smsService;
    private final SMSHistoryService smsHistoryService;


    public AuthService(ProfileRepository repository, SMSService smsService, SMSHistoryService smsHistoryService) {
        this.repository = repository;
        this.smsService = smsService;
        this.smsHistoryService = smsHistoryService;

    }

    public void isValidPhoneNumber(String phone) {
        if (!phone.matches("9[98][0-9]{10}")) {
            throw new InCorrectPhoneNumberException("In correct phone number");
        }
    }

    public ProfileResponseDTO registration(String phone) {

        isValidPhoneNumber(phone);

        Long countInMinute = smsHistoryService.getCountInMinute(phone);
        if (countInMinute >= 1) {
            throw new LimitOutPutException("Resent limit");
        }

        Optional<ProfileEntity> exists = repository.findByPhone(phone);
        ProfileEntity entity;
        if (exists.isPresent()) {
            entity = exists.get();
            if (entity.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
                repository.delete(entity);
                entity = new ProfileEntity();
                entity.setPhone(phone);
                entity.setRole(ProfileRole.ROLE_USER);
                repository.save(entity);
            } else if (entity.getStatus().equals(ProfileStatus.BLOCK)) {
                throw new ProfileStatusBlockException("This profile blocked");
            }
        } else {
            entity = new ProfileEntity();
            entity.setPhone(phone);
            entity.setRole(ProfileRole.ROLE_USER);
            repository.save(entity);
        }

        smsService.sendSMS(phone);
        return getDTO(entity);

    }


    public ProfileResponseDTO getDTO(ProfileEntity entity) {

        ProfileResponseDTO profileDTO = new ProfileResponseDTO();
        profileDTO.setId(entity.getId());
        profileDTO.setPhone(entity.getPhone());
        profileDTO.setStatus(entity.getStatus());
        profileDTO.setRole(entity.getRole());
        profileDTO.setVisible(entity.getVisible());
        profileDTO.setCreatedDate(entity.getCreatedDate());

        return profileDTO;
    }


    public AuthResponseDTO verification(VerificationDTO dto) {
        isValidPhoneNumber(dto.getPhone());

        Optional<ProfileEntity> optional = repository.findByPhone(dto.getPhone());

        if (optional.isEmpty()) {
            throw new PhoneNotFoundException("Phone not found");
        }

        ProfileEntity entity = optional.get();

        if (entity.getStatus().equals(ProfileStatus.BLOCK)) {
            throw new ProfileStatusBlockException("Profile status blocked");
        }


        if (!smsHistoryService.check(dto.getPhone(), dto.getCode())) {
            throw new IncorrectSMSCodeException("Incorrect sms code");
        }


        entity.addFireBaseToken(dto.getFirebaseToken());
        entity.setStatus(ProfileStatus.ACTIVE);

        repository.save(entity);


        return AuthResponseDTO.builder()
                .role(entity.getRole())
                .accessToken(JwtUtil.encodeAccessToken(entity.getPhone(), entity.getRole()))
                .refreshToken(JwtUtil.encodeRefreshToken(entity.getPhone(), entity.getRole()))
                .build();

    }
}
