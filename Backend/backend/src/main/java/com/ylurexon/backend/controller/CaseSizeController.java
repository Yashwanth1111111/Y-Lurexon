package com.ylurexon.backend.controller;

import com.ylurexon.backend.dto.CaseSizeRequest;
import com.ylurexon.backend.dto.CaseSizeResponse;
import com.ylurexon.backend.service.CaseSizeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/case-sizes")
public class CaseSizeController {

    private final CaseSizeService caseSizeService;

    public CaseSizeController(CaseSizeService caseSizeService) {
        this.caseSizeService = caseSizeService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CaseSizeResponse> createCaseSize(
          @Valid @RequestBody CaseSizeRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(caseSizeService.createCaseSize(request));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<CaseSizeResponse>> getAllCaseSizes() {

        return ResponseEntity.ok(
                caseSizeService.getAllCaseSizes()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CaseSizeResponse> getCaseSizeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                caseSizeService.getCaseSizeById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CaseSizeResponse> updateCaseSize(
            @PathVariable Long id,
            @RequestBody CaseSizeRequest request) {

        return ResponseEntity.ok(
                caseSizeService.updateCaseSize(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaseSize(
            @PathVariable Long id) {

        caseSizeService.deleteCaseSize(id);

        return ResponseEntity.noContent().build();
    }
}