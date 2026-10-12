package com.ylurexon.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Identity;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="case_materials")
public class CaseMaterial {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "case_material_id")
    private Long caseId;

   @Column(name = "case_material",nullable = false, unique = true,length = 100)
    private String caseMaterial;

   @CreationTimestamp
    @Column(name ="created_at",nullable=false,updatable=false)
    private LocalDateTime createdAt;

   @UpdateTimestamp
    @Column(name ="updated_at")
    private LocalDateTime updatedAt;
}
