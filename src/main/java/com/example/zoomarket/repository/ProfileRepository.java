package com.example.zoomarket.repository;


import com.example.zoomarket.entity.ProfileEntity;
import com.example.zoomarket.enums.ProfileStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long>, PagingAndSortingRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByPhone(String phone);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity  " +
            "set fullName=?2 " +
            "where id=?1")
    int editFullNameById(Long userId, String fullName);


    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity  " +
            "set phone=?2 " +
            "where id=?1")
    int updatePhoneById(Long userId, String phone);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity  " +
            "set status=?2 " +
            "where id=?1")
    void updateStatus(Long userId, ProfileStatus notActive);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity  " +
            "set email=?2 " +
            "where id=?1")
    int updateEmailById(Long userId, String email);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity  " +
            "set photoId=?2 " +
            "where id=?1")
    int updatePhotoIdById(Long userId, String photoId);
}
