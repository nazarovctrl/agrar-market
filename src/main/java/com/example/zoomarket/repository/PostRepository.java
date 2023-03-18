package com.example.zoomarket.repository;


import com.example.zoomarket.entity.AttachEntity;
import com.example.zoomarket.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long>, PagingAndSortingRepository<PostEntity, Long> {
}