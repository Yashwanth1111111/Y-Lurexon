package com.ylurexon.backend.controller;

import com.ylurexon.backend.dto.CaseMaterialRequest;
import com.ylurexon.backend.dto.CaseMaterialResponse;
import com.ylurexon.backend.service.CaseMaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/case-materials")
public class CaseMaterialController {

    private final CaseMaterialService caseMaterialService;

    public CaseMaterialController(
            CaseMaterialService caseMaterialService) {

        this.caseMaterialService = caseMaterialService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CaseMaterialResponse> createCaseMaterial(
         @Valid @RequestBody CaseMaterialRequest request) {

        CaseMaterialResponse response =
                caseMaterialService.createCaseMaterial(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<CaseMaterialResponse>> getAllCaseMaterials() {

        return ResponseEntity.ok(
                caseMaterialService.getAllCaseMaterials()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CaseMaterialResponse> getCaseMaterialById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                caseMaterialService.getCaseMaterialById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CaseMaterialResponse> updateCaseMaterial(
            @PathVariable Long id,
            @RequestBody CaseMaterialRequest request) {

        return ResponseEntity.ok(
                caseMaterialService.updateCaseMaterial(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaseMaterial(
            @PathVariable Long id) {

        caseMaterialService.deleteCaseMaterial(id);

        return ResponseEntity.noContent().build();
    }
}