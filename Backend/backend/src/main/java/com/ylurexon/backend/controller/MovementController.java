package com.ylurexon.backend.controller;

import com.ylurexon.backend.dto.MovementRequest;
import com.ylurexon.backend.dto.MovementResponse;
import com.ylurexon.backend.service.MovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movements")
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<MovementResponse> createMovement(
         @Valid @RequestBody MovementRequest request) {

        MovementResponse response =
                movementService.createMovement(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<MovementResponse>> getAllMovements() {

        List<MovementResponse> movements =
                movementService.getAllMovements();

        return ResponseEntity.ok(movements);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<MovementResponse> getMovementById(
            @PathVariable Long id) {

        MovementResponse movement =
                movementService.getMovementById(id);

        return ResponseEntity.ok(movement);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<MovementResponse> updateMovement(
            @PathVariable Long id,
            @RequestBody MovementRequest request) {

        MovementResponse updatedMovement =
                movementService.updateMovement(id, request);

        return ResponseEntity.ok(updatedMovement);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(
            @PathVariable Long id) {

        movementService.deleteMovement(id);

        return ResponseEntity.noContent().build();
    }
}