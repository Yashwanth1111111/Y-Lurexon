package com.ylurexon.backend.entity;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="watches")
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="watch_id")
    private Long watchId;

    @Column(name="watch_name",nullable = false, unique = false,length = 100)
    private String watchName;

   @ManyToOne
    @JoinColumn(name="brand_id",nullable = false)
    private Brand brand;

   @ManyToOne
    @JoinColumn(name ="movement_id",nullable = false)
    private Movement movement;

   @ManyToOne
    @JoinColumn(name="strap_material_id",nullable = false)
    private StrapMaterial strapMaterial;

   @ManyToOne
    @JoinColumn(name="glass_type_id",nullable = false)
    private GlassType glassType;

   @ManyToOne
    @JoinColumn(name="dial_colour_id",nullable = false)
    private DialColour dialColour;

    @ManyToOne
    @JoinColumn(name="case_material_id",nullable =false)
    private CaseMaterial caseMaterial;

    @ManyToOne
    @JoinColumn(name="case_size_id",nullable = false)
    private CaseSize caseSize;

    private BigDecimal price;
    @Column(name="description",columnDefinition = "Text")
    private String description;
    private Integer waterResistance;
    private Integer powerReserve;
    private Integer releaseYear;
    @Column(name="image_url",nullable = false,length = 500)
    private String imageUrl;


    @CreationTimestamp
    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at",nullable = false,updatable = false)
    private LocalDateTime updatedAt;






}
