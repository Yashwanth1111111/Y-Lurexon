package com.ylurexon.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CaseMaterialRequest {



    @NotBlank(message = "Case material is required")
    @Size(max = 100, message = "Case material cannot exceed 100 characters")
    private String caseMaterial;
}