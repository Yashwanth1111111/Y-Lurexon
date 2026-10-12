package com.ylurexon.backend.service;

import com.ylurexon.backend.dto.StrapMaterialRequest;
import com.ylurexon.backend.dto.StrapMaterialResponse;
import com.ylurexon.backend.entity.StrapMaterial;
import com.ylurexon.backend.repository.StrapMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrapMaterialService {

    private final StrapMaterialRepository strapMaterialRepository;

    public StrapMaterialService(
            StrapMaterialRepository strapMaterialRepository) {
        this.strapMaterialRepository = strapMaterialRepository;
    }

    // CREATE
    public StrapMaterialResponse createStrapMaterial(
            StrapMaterialRequest request) {

        StrapMaterial strapMaterial = new StrapMaterial();

        strapMaterial.setStrapMaterial(
                request.getStrapMaterial()
        );

        StrapMaterial savedStrapMaterial =
                strapMaterialRepository.save(strapMaterial);

        return convertToResponse(savedStrapMaterial);
    }

    // GET ALL
    public List<StrapMaterialResponse> getAllStrapMaterials() {

        List<StrapMaterial> strapMaterials =
                strapMaterialRepository.findAll();

        return strapMaterials.stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET BY ID
    public StrapMaterialResponse getStrapMaterialById(Long id) {

        StrapMaterial strapMaterial =
                strapMaterialRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Strap material not found with id: " + id
                                )
                        );

        return convertToResponse(strapMaterial);
    }

    // UPDATE
    public StrapMaterialResponse updateStrapMaterial(
            Long id,
            StrapMaterialRequest request) {

        StrapMaterial strapMaterial =
                strapMaterialRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Strap material not found with id: " + id
                                )
                        );

        strapMaterial.setStrapMaterial(
                request.getStrapMaterial()
        );

        StrapMaterial updatedStrapMaterial =
                strapMaterialRepository.save(strapMaterial);

        return convertToResponse(updatedStrapMaterial);
    }

    // DELETE
    public void deleteStrapMaterial(Long id) {

        StrapMaterial strapMaterial =
                strapMaterialRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Strap material not found with id: " + id
                                )
                        );

        strapMaterialRepository.delete(strapMaterial);
    }

    // ENTITY → RESPONSE
    private StrapMaterialResponse convertToResponse(
            StrapMaterial strapMaterial) {

        StrapMaterialResponse response =
                new StrapMaterialResponse();

        response.setStrapMaterialId(
                strapMaterial.getStrapMaterialId()
        );

        response.setStrapMaterial(
                strapMaterial.getStrapMaterial()
        );

        response.setCreatedAt(
                strapMaterial.getCreatedAt()
        );

        response.setUpdatedAt(
                strapMaterial.getUpdatedAt()
        );

        return response;
    }
}