package com.ylurexon.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlassTypeRequest {

    @NotBlank(message = "Glass type is required")

    @Size(max = 100, message = "Glass type cannot exceed 100 characters")
    private String glassType;
}