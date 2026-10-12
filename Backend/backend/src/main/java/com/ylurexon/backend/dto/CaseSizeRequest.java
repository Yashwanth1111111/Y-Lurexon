package com.ylurexon.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaseSizeRequest {

@NotBlank(message = "Case size is required")
@Size(max = 100,message ="Case size cannot exceed more than 100 Characters")
    private String caseSize;
}