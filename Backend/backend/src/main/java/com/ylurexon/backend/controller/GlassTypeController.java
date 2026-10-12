package com.ylurexon.backend.controller;

import com.ylurexon.backend.dto.GlassTypeRequest;
import com.ylurexon.backend.dto.GlassTypeResponse;
import com.ylurexon.backend.service.GlassTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/glass-types")
public class GlassTypeController {

    private final GlassTypeService glassTypeService;

    public GlassTypeController(GlassTypeService glassTypeService) {
        this.glassTypeService = glassTypeService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<GlassTypeResponse> createGlassType(
          @Valid@RequestBody GlassTypeRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(glassTypeService.createGlassType(request));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<GlassTypeResponse>> getAllGlassTypes() {

        return ResponseEntity.ok(
                glassTypeService.getAllGlassTypes()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<GlassTypeResponse> getGlassTypeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                glassTypeService.getGlassTypeById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<GlassTypeResponse> updateGlassType(
            @PathVariable Long id,
            @RequestBody GlassTypeRequest request) {

        return ResponseEntity.ok(
                glassTypeService.updateGlassType(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGlassType(
            @PathVariable Long id) {

        glassTypeService.deleteGlassType(id);

        return ResponseEntity.noContent().build();
    }
}