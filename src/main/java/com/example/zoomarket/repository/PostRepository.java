package com.example.zoomarket.repository;


import com.example.zoomarket.entity.PostEntity;
import com.example.zoomarket.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long>, PagingAndSortingRepository<PostEntity, Long> {
    Optional<PostEntity> findByIdAndVisibleTrue(Long id);

    Page<PostEntity> findByVisibleTrueOrderByCreatedDateDescLikeCountDesc(Pageable pageable);
    Page<PostEntity> findByVisibleTrueAndTypeCategoryTypeOrderByCreatedDateDescLikeCountDesc(Pageable pageable, Type type);
    Page<PostEntity> findByVisibleTrueAndProfileIdAndTypeCategoryTypeOrderByCreatedDateDescLikeCountDesc(Pageable pageable, Long profileId, Type type);
}