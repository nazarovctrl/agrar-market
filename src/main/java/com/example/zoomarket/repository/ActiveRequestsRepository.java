package com.example.zoomarket.repository;

import com.example.zoomarket.entity.ActiveRequests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActiveRequestsRepository extends JpaRepository<ActiveRequests, Integer> {
    List<ActiveRequests> findAllByCreatedTimeBetween(LocalDateTime createdTime, LocalDateTime createdTime2);
}
