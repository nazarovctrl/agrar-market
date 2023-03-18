package com.example.zoomarket.repository;


import com.example.zoomarket.entity.PostLikeEntity;
import com.example.zoomarket.entity.PostTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends CrudRepository<PostLikeEntity, Long>, PagingAndSortingRepository<PostLikeEntity, Long> {
    Optional<PostLikeEntity> findByPostIdAndProfileId(Long postId, Long profileId);
}