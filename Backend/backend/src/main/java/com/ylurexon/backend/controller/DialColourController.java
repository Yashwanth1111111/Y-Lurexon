package com.ylurexon.backend.controller;

import com.ylurexon.backend.dto.DialColourRequest;
import com.ylurexon.backend.dto.DialColourResponse;
import com.ylurexon.backend.service.DialColourService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dial-colours")
public class DialColourController {

    private final DialColourService dialColourService;

    public DialColourController(
            DialColourService dialColourService) {

        this.dialColourService = dialColourService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<DialColourResponse> createDialColour(
          @Valid @RequestBody DialColourRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dialColourService.createDialColour(request));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<DialColourResponse>> getAllDialColours() {

        return ResponseEntity.ok(
                dialColourService.getAllDialColours()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DialColourResponse> getDialColourById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                dialColourService.getDialColourById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<DialColourResponse> updateDialColour(
            @PathVariable Long id,
            @RequestBody DialColourRequest request) {

        return ResponseEntity.ok(
                dialColourService.updateDialColour(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDialColour(
            @PathVariable Long id) {

        dialColourService.deleteDialColour(id);

        return ResponseEntity.noContent().build();
    }
}