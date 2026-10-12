package com.ylurexon.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="Case_Sizes")
public class CaseSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_size_id",nullable = false,length = 100)
    private Long caseSizeId;

    @Column(name = "case_size",nullable = false,unique = true,length = 100)
    private String caseSize;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updateTime;





}
