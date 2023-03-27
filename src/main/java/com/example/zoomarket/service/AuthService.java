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
    private final SMSService phoneService;
    private final SMSHistoryService phoneHistoryService;


    public AuthService(ProfileRepository repository, SMSService phoneService, SMSHistoryService phoneHistoryService) {
        this.repository = repository;
        this.phoneService = phoneService;
        this.phoneHistoryService = phoneHistoryService;

    }


    public ProfileResponseDTO registration(String phone) {

        Optional<ProfileEntity> exists = repository.findByPhone(phone);
        if (exists.isPresent()) {
            ProfileEntity entity = exists.get();
            if (entity.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
                repository.delete(entity);
            }
        }

        Long countInMinute = phoneHistoryService.getCountInMinute(phone);
        if (countInMinute > 4) {
            throw new LimitOutPutException("Resent limit");
        }


        ProfileEntity entity = new ProfileEntity();
        entity.setPhone(phone);
        entity.setRole(ProfileRole.ROLE_USER);
        repository.save(entity);

        //TODO send sms


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

        Optional<ProfileEntity> optional = repository.findByPhone(dto.getPhone());

        if (optional.isEmpty()) {
            throw new PhoneNotFoundException("Phone not found");
        }

        ProfileEntity entity = optional.get();

        if (entity.getStatus().equals(ProfileStatus.BLOCK)) {
            throw new ProfileStatusBlockException("Profile status blocked");
        }

        //TODO  ob tashash kere sms provider ulangandan keyin mazgi
        if (!dto.getCode().equals("2222")) {

            if (!phoneHistoryService.check(dto.getPhone(), dto.getCode())) {
                throw new IncorrectSMSCodeException("Incorrect sms code");
            }
        }


        entity.setStatus(ProfileStatus.ACTIVE);

        repository.save(entity);

        AuthResponseDTO responseDTO = new AuthResponseDTO();
        responseDTO.setRole(entity.getRole());
        responseDTO.setToken(JwtUtil.encode(entity.getPhone(), entity.getRole()));

        return responseDTO;

    }
}
