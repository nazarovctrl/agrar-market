package com.example.zoomarket.entity;

import com.example.zoomarket.enums.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Embeddable
@Getter
@Setter
@Entity
@Table(name = "type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attach_id")
    private String attachId;

    @ManyToOne
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;

    @Column
    private String name;

    @Column(name = "category_id")
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;
}
