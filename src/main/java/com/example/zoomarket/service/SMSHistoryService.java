package com.example.zoomarket.service;


import com.example.zoomarket.dto.email.SMSHistoryResponseDTO;
import com.example.zoomarket.entity.SMSHistoryEntity;
import com.example.zoomarket.exp.sms.IncorrectDateFormatException;
import com.example.zoomarket.exp.sms.SMSHistoryNotFoundException;
import com.example.zoomarket.repository.SMSHistoryRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SMSHistoryService {
    private final SMSHistoryRepository repository;

    public SMSHistoryService(SMSHistoryRepository repository) {
        this.repository = repository;
    }


    public void create(SMSHistoryEntity entity) {
        repository.save(entity);
    }

    public List<SMSHistoryResponseDTO> getByEmail(String phone) {
        List<SMSHistoryEntity> entityList = repository.findByPhone(phone);

        List<SMSHistoryResponseDTO> dtoList = new ArrayList<>();

        entityList.forEach(entity -> dtoList.add(getDTO(entity)));
        return dtoList;

    }

    private SMSHistoryResponseDTO getDTO(SMSHistoryEntity entity) {
        SMSHistoryResponseDTO dto = new SMSHistoryResponseDTO();
        dto.setPhone(entity.getPhone());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<SMSHistoryResponseDTO> getByDate(String date) {
        LocalDate cd;
        try {
            cd = LocalDate.parse(date);
        } catch (IncorrectDateFormatException e) {
            throw new IncorrectDateFormatException(e.getMessage());
        }

        List<SMSHistoryEntity> entityList = repository.findByCreatedDate(cd);
        List<SMSHistoryResponseDTO> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(getDTO(entity)));
        return dtoList;
    }

    public Page<SMSHistoryResponseDTO> getList(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "created_date");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<SMSHistoryEntity> pageObj = repository.findAll(pageable);
        List<SMSHistoryEntity> entityList = pageObj.getContent();

        List<SMSHistoryResponseDTO> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(getDTO(entity)));

        return new PageImpl<>(dtoList, pageable, pageObj.getTotalElements());
    }


    public Long getCountInMinute(String email) {

        LocalDateTime toDate = LocalDateTime.now();
        LocalDateTime fromDate = toDate.minusMinutes(1);

        return repository.countByPhoneAndCreatedDateBetween(email, fromDate, toDate);
    }

    public Boolean check(String phone, String code) {
        Optional<SMSHistoryEntity> optional = repository.findByPhoneOrderByCreatedDate(phone);
        if (optional.isEmpty()) {
            throw new SMSHistoryNotFoundException("Sms history not found");
        }

        SMSHistoryEntity entity = optional.get();

        return entity.getCode().equals(code);

    }
}
