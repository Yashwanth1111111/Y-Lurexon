package com.ylurexon.backend.service;

import com.ylurexon.backend.dto.CaseSizeRequest;
import com.ylurexon.backend.dto.CaseSizeResponse;
import com.ylurexon.backend.entity.CaseSize;
import com.ylurexon.backend.repository.CaseSizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseSizeService {

    private final CaseSizeRepository caseSizeRepository;

    public CaseSizeService(CaseSizeRepository caseSizeRepository) {
        this.caseSizeRepository = caseSizeRepository;
    }

    // CREATE
    public CaseSizeResponse createCaseSize(CaseSizeRequest request) {

        CaseSize caseSize = new CaseSize();

        caseSize.setCaseSize(request.getCaseSize());

        CaseSize savedCaseSize = caseSizeRepository.save(caseSize);

        return convertToResponse(savedCaseSize);
    }

    // GET ALL
    public List<CaseSizeResponse> getAllCaseSizes() {

        List<CaseSize> caseSizes = caseSizeRepository.findAll();

        return caseSizes.stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET BY ID
    public CaseSizeResponse getCaseSizeById(Long id) {

        CaseSize caseSize = caseSizeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Case size not found with id: " + id)
                );

        return convertToResponse(caseSize);
    }

    // UPDATE
    public CaseSizeResponse updateCaseSize(
            Long id,
            CaseSizeRequest request) {

        CaseSize caseSize = caseSizeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Case size not found with id: " + id)
                );

        caseSize.setCaseSize(request.getCaseSize());

        CaseSize updatedCaseSize =
                caseSizeRepository.save(caseSize);

        return convertToResponse(updatedCaseSize);
    }

    // DELETE
    public void deleteCaseSize(Long id) {

        CaseSize caseSize = caseSizeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Case size not found with id: " + id)
                );

        caseSizeRepository.delete(caseSize);
    }

    // ENTITY → RESPONSE DTO
    private CaseSizeResponse convertToResponse(CaseSize caseSize) {

        CaseSizeResponse response = new CaseSizeResponse();

        response.setCaseSizeId(caseSize.getCaseSizeId());
        response.setCaseSize(caseSize.getCaseSize());
        response.setCreateTime(caseSize.getCreateTime());
        response.setUpdateTime(caseSize.getUpdateTime());

        return response;
    }
}