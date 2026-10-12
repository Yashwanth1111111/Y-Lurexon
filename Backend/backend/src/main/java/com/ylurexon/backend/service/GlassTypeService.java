package com.ylurexon.backend.service;

import com.ylurexon.backend.dto.GlassTypeRequest;
import com.ylurexon.backend.dto.GlassTypeResponse;
import com.ylurexon.backend.entity.GlassType;
import com.ylurexon.backend.repository.GlassTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlassTypeService {

    private final GlassTypeRepository glassTypeRepository;

    public GlassTypeService(GlassTypeRepository glassTypeRepository) {
        this.glassTypeRepository = glassTypeRepository;
    }

    // CREATE
    public GlassTypeResponse createGlassType(GlassTypeRequest request) {

        GlassType glassType = new GlassType();

        glassType.setGlassType(request.getGlassType());

        GlassType savedGlassType =
                glassTypeRepository.save(glassType);

        return convertToResponse(savedGlassType);
    }

    // GET ALL
    public List<GlassTypeResponse> getAllGlassTypes() {

        List<GlassType> glassTypes =
                glassTypeRepository.findAll();

        return glassTypes.stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET BY ID
    public GlassTypeResponse getGlassTypeById(Long id) {

        GlassType glassType =
                glassTypeRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Glass type not found with id: " + id
                                )
                        );

        return convertToResponse(glassType);
    }

    // UPDATE
    public GlassTypeResponse updateGlassType(
            Long id,
            GlassTypeRequest request) {

        GlassType glassType =
                glassTypeRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Glass type not found with id: " + id
                                )
                        );

        glassType.setGlassType(request.getGlassType());

        GlassType updatedGlassType =
                glassTypeRepository.save(glassType);

        return convertToResponse(updatedGlassType);
    }

    // DELETE
    public void deleteGlassType(Long id) {

        GlassType glassType =
                glassTypeRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Glass type not found with id: " + id
                                )
                        );

        glassTypeRepository.delete(glassType);
    }

    // ENTITY → RESPONSE DTO
    private GlassTypeResponse convertToResponse(GlassType glassType) {

        GlassTypeResponse response = new GlassTypeResponse();

        response.setGlassTypeId(glassType.getGlassTypeId());
        response.setGlassType(glassType.getGlassType());
        response.setCreatedAt(glassType.getCreatedAt());
        response.setUpdatedAt(glassType.getUpdatedAt());

        return response;
    }
}