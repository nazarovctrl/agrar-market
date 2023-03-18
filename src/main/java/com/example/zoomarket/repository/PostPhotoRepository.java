package com.example.zoomarket.repository;


import com.example.zoomarket.entity.PostPhotoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostPhotoRepository extends CrudRepository<PostPhotoEntity, Long>, PagingAndSortingRepository<PostPhotoEntity, Long> {
}