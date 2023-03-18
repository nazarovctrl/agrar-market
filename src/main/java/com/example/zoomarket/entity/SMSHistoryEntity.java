package com.example.zoomarket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "phone_history")
public class SMSHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String phone;
    @Column

    private String code;
    @Column(name = "created_date")

    private LocalDateTime createdDate = LocalDateTime.now();
}
