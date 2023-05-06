package com.example.zoomarket.repository;


import com.example.zoomarket.entity.CategoryEntity;
import com.example.zoomarket.entity.TypeEntity;
import com.example.zoomarket.enums.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends CrudRepository<TypeEntity, Long>, PagingAndSortingRepository<TypeEntity, Long> {
    List<TypeEntity> findByCategoryIdOrderById(Long categoryId);
}