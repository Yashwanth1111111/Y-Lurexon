package com.ylurexon.backend.service;

import com.ylurexon.backend.dto.CaseMaterialRequest;
import com.ylurexon.backend.dto.CaseMaterialResponse;
import com.ylurexon.backend.entity.CaseMaterial;
import com.ylurexon.backend.repository.CaseMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseMaterialService {

    private final CaseMaterialRepository caseMaterialRepository;

    public CaseMaterialService(CaseMaterialRepository caseMaterialRepository) {
        this.caseMaterialRepository = caseMaterialRepository;
    }

    // CREATE
    public CaseMaterialResponse createCaseMaterial(CaseMaterialRequest request) {

        CaseMaterial caseMaterial = new CaseMaterial();

        caseMaterial.setCaseMaterial(request.getCaseMaterial());

        CaseMaterial savedCaseMaterial =
                caseMaterialRepository.save(caseMaterial);

        return convertToResponse(savedCaseMaterial);
    }

    // GET ALL
    public List<CaseMaterialResponse> getAllCaseMaterials() {

        List<CaseMaterial> caseMaterials =
                caseMaterialRepository.findAll();

        return caseMaterials.stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET BY ID
    public CaseMaterialResponse getCaseMaterialById(Long id) {

        CaseMaterial caseMaterial =
                caseMaterialRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Case material not found with id: " + id
                                )
                        );

        return convertToResponse(caseMaterial);
    }

    // UPDATE
    public CaseMaterialResponse updateCaseMaterial(
            Long id,
            CaseMaterialRequest request) {

        CaseMaterial caseMaterial =
                caseMaterialRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Case material not found with id: " + id
                                )
                        );

        caseMaterial.setCaseMaterial(request.getCaseMaterial());

        CaseMaterial updatedCaseMaterial =
                caseMaterialRepository.save(caseMaterial);

        return convertToResponse(updatedCaseMaterial);
    }

    // DELETE
    public void deleteCaseMaterial(Long id) {

        CaseMaterial caseMaterial =
                caseMaterialRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Case material not found with id: " + id
                                )
                        );

        caseMaterialRepository.delete(caseMaterial);
    }

    // ENTITY → RESPONSE DTO
    private CaseMaterialResponse convertToResponse(
            CaseMaterial caseMaterial) {

        CaseMaterialResponse response =
                new CaseMaterialResponse();

        response.setCaseId(caseMaterial.getCaseId());
        response.setCaseMaterial(caseMaterial.getCaseMaterial());
        response.setCreatedAt(caseMaterial.getCreatedAt());
        response.setUpdatedAt(caseMaterial.getUpdatedAt());

        return response;
    }
}