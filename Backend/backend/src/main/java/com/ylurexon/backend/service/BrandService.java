package com.ylurexon.backend.service;

import com.ylurexon.backend.dto.BrandRequest;
import com.ylurexon.backend.dto.BrandResponse;
import com.ylurexon.backend.entity.Brand;
import com.ylurexon.backend.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // CREATE
    public BrandResponse createBrand(BrandRequest request) {

        Brand brand = new Brand();

        brand.setBrandName(request.getBrandName());
        brand.setCountry(request.getCountry());
        brand.setHistory(request.getHistory());
        brand.setLogoUrl(request.getLogoUrl());

        Brand savedBrand = brandRepository.save(brand);

        return convertToResponse(savedBrand);
    }

    // GET ALL
    public List<BrandResponse> getAllBrands() {

        List<Brand> brands = brandRepository.findAll();

        return brands.stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET BY ID
    public BrandResponse getBrandById(Long id) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Brand not found with id: " + id)
                );

        return convertToResponse(brand);
    }

    // UPDATE
    public BrandResponse updateBrand(Long id, BrandRequest request) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Brand not found with id: " + id)
                );

        brand.setBrandName(request.getBrandName());
        brand.setCountry(request.getCountry());
        brand.setHistory(request.getHistory());
        brand.setLogoUrl(request.getLogoUrl());

        Brand updatedBrand = brandRepository.save(brand);

        return convertToResponse(updatedBrand);
    }

    // DELETE
    public void deleteBrand(Long id) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Brand not found with id: " + id)
                );

        brandRepository.delete(brand);
    }

    // ENTITY → RESPONSE DTO
    private BrandResponse convertToResponse(Brand brand) {

        BrandResponse response = new BrandResponse();

        response.setBrandId(brand.getBrandId());
        response.setBrandName(brand.getBrandName());
        response.setCountry(brand.getCountry());
        response.setHistory(brand.getHistory());
        response.setLogoUrl(brand.getLogoUrl());
        response.setCreatedAt(brand.getCreatedAt());
        response.setUpdatedAt(brand.getUpdatedAt());

        return response;
    }
}