package com.example.zoomarket.repository;


import com.example.zoomarket.entity.AttachEntity;
import com.example.zoomarket.entity.PostTypeEntity;
import com.example.zoomarket.enums.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTypeRepository extends CrudRepository<PostTypeEntity, Long>, PagingAndSortingRepository<PostTypeEntity, Long> {
    List<PostTypeEntity> findByType(Type type);
}