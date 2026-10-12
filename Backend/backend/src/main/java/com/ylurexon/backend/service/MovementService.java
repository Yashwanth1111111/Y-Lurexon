package com.ylurexon.backend.service;

import com.ylurexon.backend.dto.MovementRequest;
import com.ylurexon.backend.dto.MovementResponse;
import com.ylurexon.backend.entity.Movement;
import com.ylurexon.backend.repository.MovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService {

    private final MovementRepository movementRepository;

    public MovementService(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    // CREATE
    public MovementResponse createMovement(MovementRequest request) {

        Movement movement = new Movement();

        movement.setMovementName(request.getMovementName());

        Movement savedMovement = movementRepository.save(movement);

        return convertToResponse(savedMovement);
    }

    // GET ALL
    public List<MovementResponse> getAllMovements() {

        List<Movement> movements = movementRepository.findAll();

        return movements.stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET BY ID
    public MovementResponse getMovementById(Long id) {

        Movement movement = movementRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Movement not found with id: " + id
                        )
                );

        return convertToResponse(movement);
    }

    // UPDATE
    public MovementResponse updateMovement(
            Long id,
            MovementRequest request) {

        Movement movement = movementRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Movement not found with id: " + id
                        )
                );

        movement.setMovementName(request.getMovementName());

        Movement updatedMovement = movementRepository.save(movement);

        return convertToResponse(updatedMovement);
    }

    // DELETE
    public void deleteMovement(Long id) {

        Movement movement = movementRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Movement not found with id: " + id
                        )
                );

        movementRepository.delete(movement);
    }

    // ENTITY → RESPONSE DTO
    private MovementResponse convertToResponse(Movement movement) {

        MovementResponse response = new MovementResponse();

        response.setMovementId(movement.getMovementId());
        response.setMovementName(movement.getMovementName());
        response.setCreatedAt(movement.getCreatedAt());
        response.setUpdatedAt(movement.getUpdatedAt());

        return response;
    }
}