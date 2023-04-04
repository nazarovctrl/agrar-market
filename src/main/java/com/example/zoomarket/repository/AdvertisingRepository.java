package com.example.zoomarket.repository;

import com.example.zoomarket.entity.AdvertisingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisingRepository extends JpaRepository<AdvertisingEntity, Integer>, PagingAndSortingRepository<AdvertisingEntity, Integer> {

}
