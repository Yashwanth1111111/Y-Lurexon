package com.ylurexon.backend.controller;

import com.ylurexon.backend.dto.StrapMaterialRequest;
import com.ylurexon.backend.dto.StrapMaterialResponse;
import com.ylurexon.backend.service.StrapMaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/strap-materials")
public class StrapMaterialController {

    private final StrapMaterialService strapMaterialService;

    public StrapMaterialController(
            StrapMaterialService strapMaterialService) {
        this.strapMaterialService = strapMaterialService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<StrapMaterialResponse> createStrapMaterial(
            @RequestBody @Valid StrapMaterialRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        strapMaterialService.createStrapMaterial(request)
                );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<StrapMaterialResponse>>
    getAllStrapMaterials() {

        return ResponseEntity.ok(
                strapMaterialService.getAllStrapMaterials()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StrapMaterialResponse> getStrapMaterialById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                strapMaterialService.getStrapMaterialById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<StrapMaterialResponse> updateStrapMaterial(
            @PathVariable Long id,
            @RequestBody StrapMaterialRequest request) {

        return ResponseEntity.ok(
                strapMaterialService.updateStrapMaterial(
                        id,
                        request
                )
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStrapMaterial(
            @PathVariable Long id) {

        strapMaterialService.deleteStrapMaterial(id);

        return ResponseEntity.noContent().build();
    }
}