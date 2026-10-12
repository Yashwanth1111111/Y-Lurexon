package com.ylurexon.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FavoriteResponse {

    private Long watchId;
    private String watchName;
    private BigDecimal price;
    private String imageUrl;

    public FavoriteResponse(
            Long watchId,
            String watchName,
            BigDecimal price,
            String imageUrl) {

        this.watchId = watchId;
        this.watchName = watchName;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}