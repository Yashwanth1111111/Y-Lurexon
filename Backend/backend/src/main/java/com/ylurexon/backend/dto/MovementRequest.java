package com.ylurexon.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MovementRequest {
@NotBlank(message = "Movement name cannot be empty")
@Size(max = 100,message = "Movement name cannot be more than 100 Characters")
    private String movementName;

    public String getMovementName() {
        return movementName;
    }

    public void setMovementName(String movementName) {
        this.movementName = movementName;
    }



}
