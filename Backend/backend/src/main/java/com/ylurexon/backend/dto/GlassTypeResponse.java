package com.ylurexon.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GlassTypeResponse {

    private Long glassTypeId;
    private String glassType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}