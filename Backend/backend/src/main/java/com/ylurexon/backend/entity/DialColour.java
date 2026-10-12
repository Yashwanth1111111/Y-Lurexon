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
@Table(name="dial_colour")
public class DialColour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dial_colour_id")
    private Long dialColourId;

    @Column(name="dial_colour",nullable = false, unique = true,length = 100)
    private String dialColour;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;


}
