package com.example.zoomarket.entity;

import com.example.zoomarket.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_id")
    private Long typeId;

    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private TypeEntity type;

    @Column(name = "profile_id")
    private Long profileId;

    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column
    private String title;

    @Column
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column
    private Currency currency;
    @Column
    private String phone;

    @Column
    private String location;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private Long likeCount = 0L;

    @Column
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    private Boolean visible = true;
}
