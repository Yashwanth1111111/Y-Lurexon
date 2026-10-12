package com.ylurexon.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DialColourResponse {

    private Long dialColourId;
    private String dialColour;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}