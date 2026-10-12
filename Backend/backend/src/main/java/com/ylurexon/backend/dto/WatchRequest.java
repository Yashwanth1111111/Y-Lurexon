package com.ylurexon.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WatchRequest {

    @NotBlank(message = "Watch name is required")
    @Size(max = 100, message = "Watch name cannot exceed 100 characters")
    private String watchName;

    @NotNull(message = "Brand ID is required")
    private Long brandId;

    @NotNull(message = "Movement ID is required")
    private Long movementId;

    @NotNull(message = "Strap material ID is required")
    private Long strapMaterialId;

    @NotNull(message = "Glass type ID is required")
    private Long glassTypeId;

    @NotNull(message = "Dial colour ID is required")
    private Long dialColourId;

    @NotNull(message = "Case material ID is required")
    private Long caseMaterialId;

    @NotNull(message = "Case size ID is required")
    private Long caseSizeId;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    private String description;

    @Min(value = 0, message = "Water resistance cannot be negative")
    private Integer waterResistance;

    @Min(value = 0, message = "Power reserve cannot be negative")
    private Integer powerReserve;

    @Min(value = 1900, message = "Release year must be valid")
    private Integer releaseYear;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;
}