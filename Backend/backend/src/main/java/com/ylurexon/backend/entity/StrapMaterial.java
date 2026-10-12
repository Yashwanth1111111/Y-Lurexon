package com.ylurexon.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name= "strap_materials")
public class StrapMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="strap_material_id")
    private Long strapMaterialId;

    @Column(name = "strap_material",nullable = false,unique = true,length = 100)
    private String strapMaterial;


    @CreationTimestamp
    @Column(name="created_at",nullable = false, updatable = false )
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false, updatable = false)
    private LocalDateTime updatedAt;
}
