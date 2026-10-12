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

@Entity// this identifies so here to create a table
@Table(name = "brands")//table name
public class Brand {

    @Id//unique thing
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto genetrated in the sql
    @Column(name = "brand_id")//col name
    private Long brandId;

    @Column(name = "brand_name", nullable = false, unique = true, length = 100)//col name nullable means is it mandatory or not length means lengtht of it
    private String brandName;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @Column(name = "history", columnDefinition = "TEXT")//column defination where the most of the text goes on
    private String history;

    @Column(name = "logo_url", nullable = false, length = 255)
    private String logoUrl;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false) //updateable means can it be changed again we said no becasue the watche is already created
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;




}