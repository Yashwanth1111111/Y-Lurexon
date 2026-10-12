package com.ylurexon.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StrapMaterialRequest {

    @NotBlank(message = "Strap material is required")

    @Size(max = 100, message = "Strap material cannot exceed 100 characters")
    private String strapMaterial;
}