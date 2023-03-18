package com.example.zoomarket.repository;


import com.example.zoomarket.entity.AttachEntity;
import com.example.zoomarket.entity.PostTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTypeRepository extends CrudRepository<PostTypeEntity, Long>, PagingAndSortingRepository<PostTypeEntity, Long> {
}