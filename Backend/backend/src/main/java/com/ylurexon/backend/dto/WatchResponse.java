package com.ylurexon.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class WatchResponse {

    private Long watchId;

    private String watchName;

    private Long brandId;
    private Long movementId;
    private Long strapMaterialId;
    private Long glassTypeId;
    private Long dialColourId;
    private Long caseMaterialId;
    private Long caseSizeId;

    private BigDecimal price;
    private String description;
    private Integer waterResistance;
    private Integer powerReserve;
    private Integer releaseYear;
    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}