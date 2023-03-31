package com.example.zoomarket.service;


import com.example.zoomarket.dto.profile.ProfileDetailDTO;
import com.example.zoomarket.entity.ProfileEntity;
import com.example.zoomarket.enums.ProfileStatus;
import com.example.zoomarket.exp.profile.ProfileNotFoundException;
import com.example.zoomarket.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileDetailDTO getDetail(Long userId) {
        Optional<ProfileEntity> optional = profileRepository.findById(userId);
        if (optional.isEmpty()) {
            throw new ProfileNotFoundException("Profile not found");
        }
        ProfileEntity profileEntity = optional.get();

        ProfileDetailDTO dto = new ProfileDetailDTO();
        dto.setFullName(profileEntity.getFullName());
        dto.setPhone(profileEntity.getPhone());
        dto.setPhotoId(profileEntity.getPhotoId());
        dto.setEmail(profileEntity.getEmail());

        return dto;
    }

    public Boolean editFullName(Long userId, String fullName) {
        int r = profileRepository.editFullNameById(userId, fullName);

        return r == 1;
    }

    public Boolean changePhone(Long userId, String phone) {
        int r = profileRepository.updatePhoneById(userId, phone);

        if (r != 1) {
            return false;
        }

        profileRepository.updateStatus(userId, ProfileStatus.NOT_ACTIVE);

        //TODO send sms
//        Thread thread = new Thread() {
//            @Override
//            public synchronized void start() {
//                String sb = "Salom qalaysan \n" +
//                        "Bu test message" +
//                        "Click the link : " + appUrl + "/auth/verification/email/" +
//                        JwtUtil.encode(entity.getPhone(), ProfileRole.ROLE_USER);
//                mailService.sendSMS(dto.getPhone(), "Complete Registration", sb);
//
//                PhoneHistoryEntity emailHistoryEntity = new PhoneHistoryEntity();
//                emailHistoryEntity.setPhone(dto.getPhone());
//                emailHistoryEntity.setMessage(sb);
//                emailHistoryEntity.setCreatedDate(LocalDateTime.now());
//
//                emailHistoryService.create(emailHistoryEntity);
//            }
//        };
//        thread.start();

        return true;
    }


    public Boolean changeEmail(Long userId, String email) {
        int r = profileRepository.updateEmailById(userId, email);
        return r == 1;
    }


    public Boolean changePhoto(Long userId, String photoId) {
        int r = profileRepository.updatePhotoIdById(userId, photoId);
        return r == 1;
    }
}
