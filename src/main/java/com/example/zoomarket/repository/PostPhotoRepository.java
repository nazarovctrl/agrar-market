package com.example.zoomarket.repository;


import com.example.zoomarket.entity.PostPhotoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostPhotoRepository extends CrudRepository<PostPhotoEntity, Long>, PagingAndSortingRepository<PostPhotoEntity, Long> {
    List<PostPhotoEntity> findByPostId(Long postId);
}