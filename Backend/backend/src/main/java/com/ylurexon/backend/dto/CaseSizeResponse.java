package com.ylurexon.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CaseSizeResponse {

    private Long caseSizeId;
    private String caseSize;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}