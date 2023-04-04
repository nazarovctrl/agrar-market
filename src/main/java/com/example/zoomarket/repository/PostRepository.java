package com.example.zoomarket.repository;


import com.example.zoomarket.entity.PostEntity;
import com.example.zoomarket.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long>, PagingAndSortingRepository<PostEntity, Long> {
    Optional<PostEntity> findByIdAndVisibleTrue(Long id);

    Page<PostEntity> findByVisibleTrueOrderByLikeCount(Pageable pageable);
    Page<PostEntity> findByVisibleTrueAndTypeTypeOrderByLikeCountDesc(Pageable pageable, Type type);
    Page<PostEntity> findByVisibleTrueAndProfileIdAndTypeTypeOrderByLikeCountDesc(Pageable pageable, Long profileId, Type type);
    int countAllByCreatedDateBetween(LocalDateTime joinedDate, LocalDateTime joinedDate2);
}