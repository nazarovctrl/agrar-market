package com.example.zoomarket.repository;

import com.example.zoomarket.entity.SMSHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface SMSHistoryRepository extends CrudRepository<SMSHistoryEntity, Integer>, PagingAndSortingRepository<SMSHistoryEntity, Integer> {
    List<SMSHistoryEntity> findByPhone(String phone);


    @Query(value = "SELECT * from phone_history e where to_date(to_char(e.created_date, 'YYYY/MM/DD'), 'YYYY/MM/DD')=?1", nativeQuery = true)
    List<SMSHistoryEntity> findByCreatedDate(LocalDate createdDate);

    long countByPhoneAndCreatedDateBetween(String email, LocalDateTime fromDate, LocalDateTime toDate);

    Optional<SMSHistoryEntity> findByPhoneOrderByCreatedDate(String phone);
}
