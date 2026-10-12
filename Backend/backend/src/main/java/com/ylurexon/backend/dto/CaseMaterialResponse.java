package com.ylurexon.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CaseMaterialResponse {

    private Long caseId;

    private String caseMaterial;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}