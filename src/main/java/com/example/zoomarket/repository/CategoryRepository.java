package com.example.zoomarket.repository;


import com.example.zoomarket.entity.CategoryEntity;
import com.example.zoomarket.enums.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long>, PagingAndSortingRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByType(Type type);
}