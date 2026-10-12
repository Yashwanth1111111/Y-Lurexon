package com.ylurexon.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DialColourRequest {

    @NotBlank(message = "Dial colour is required")
   @Size(max = 100, message = "Dial colour cannot exceed 100 characters")
    private String dialColour;
}