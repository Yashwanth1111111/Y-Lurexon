package com.ylurexon.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StrapMaterialResponse {

    private Long strapMaterialId;
    private String strapMaterial;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}