package com.ylurexon.backend.controller;

import com.ylurexon.backend.dto.BrandRequest;
import com.ylurexon.backend.dto.BrandResponse;
import com.ylurexon.backend.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public BrandResponse createBrand(
            @Valid @RequestBody BrandRequest request) {
        return brandService.createBrand(request);
    }

    @GetMapping
    public List<BrandResponse> getAllBrands() {
        return brandService.getAllBrands();


    }
    @GetMapping("/{id}")
    public BrandResponse getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }
    @PutMapping("/{id}")
    public BrandResponse updateBrand(
            @PathVariable Long id,
            @RequestBody BrandRequest request) {

        return brandService.updateBrand(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBrandById(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }
}