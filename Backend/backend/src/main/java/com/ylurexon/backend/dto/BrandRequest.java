package com.ylurexon.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BrandRequest {

@NotBlank(message = "Brand Name is Required")
@Size(max=100,message = "Brand name cannot exceed more than 100 characters")
    private String brandName;

@NotBlank(message = "Country is required")
@Size(max = 100,message = "Country cannot exceed more than 100 Characters")
    private String country;
    private String history;
    private String logoUrl;

    public BrandRequest(){

    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;

    }
    public String getHistory() {
        return history;
    }
    public void setHistory(String history) {
        this.history = history;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}

