package com.ylurexon.backend.controller;

import com.ylurexon.backend.dto.PageResponse;
import com.ylurexon.backend.dto.WatchRequest;
import com.ylurexon.backend.dto.WatchResponse;
import com.ylurexon.backend.service.WatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/watches")
public class WatchController {

    private final WatchService watchService;

    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<WatchResponse> createWatch(
            @Valid @RequestBody WatchRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(watchService.createWatch(request));
    }

    // GET ALL / SEARCH / FILTER / SORT / PAGINATION
    @GetMapping
    public ResponseEntity<PageResponse<WatchResponse>> getAllWatches(

            @RequestParam(required = false)
            String search,

            @RequestParam(required = false)
            Long brandId,

            @RequestParam(required = false)
            Long movementId,

            @RequestParam(required = false)
            Long strapMaterialId,

            @RequestParam(required = false)
            Long glassTypeId,

            @RequestParam(required = false)
            Long dialColourId,

            @RequestParam(required = false)
            Long caseMaterialId,

            @RequestParam(required = false)
            Long caseSizeId,

            @RequestParam(required = false)
            BigDecimal minPrice,

            @RequestParam(required = false)
            BigDecimal maxPrice,

            @RequestParam(required = false)
            Integer minReleaseYear,

            @RequestParam(required = false)
            Integer maxReleaseYear,

            @RequestParam(
                    required = false,
                    defaultValue = "name_asc"
            )
            String sort,

            @RequestParam(
                    defaultValue = "0"
            )
            int page,

            @RequestParam(
                    defaultValue = "10"
            )
            int size) {

        return ResponseEntity.ok(
                watchService.getAllWatches(
                        search,
                        brandId,
                        movementId,
                        strapMaterialId,
                        glassTypeId,
                        dialColourId,
                        caseMaterialId,
                        caseSizeId,
                        minPrice,
                        maxPrice,
                        minReleaseYear,
                        maxReleaseYear,
                        sort,
                        page,
                        size
                )
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<WatchResponse> getWatchById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                watchService.getWatchById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<WatchResponse> updateWatch(
            @PathVariable Long id,
            @Valid @RequestBody WatchRequest request) {

        return ResponseEntity.ok(
                watchService.updateWatch(id, request)
        );
    }

    // DELETE - ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatch(
            @PathVariable Long id) {

        watchService.deleteWatch(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}