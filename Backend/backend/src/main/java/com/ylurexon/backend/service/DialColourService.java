package com.ylurexon.backend.service;

import com.ylurexon.backend.dto.DialColourRequest;
import com.ylurexon.backend.dto.DialColourResponse;
import com.ylurexon.backend.entity.DialColour;
import com.ylurexon.backend.repository.DialColourRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DialColourService {

    private final DialColourRepository dialColourRepository;

    public DialColourService(DialColourRepository dialColourRepository) {
        this.dialColourRepository = dialColourRepository;
    }

    // CREATE
    public DialColourResponse createDialColour(DialColourRequest request) {

        DialColour dialColour = new DialColour();

        dialColour.setDialColour(request.getDialColour());

        DialColour savedDialColour =
                dialColourRepository.save(dialColour);

        return convertToResponse(savedDialColour);
    }

    // GET ALL
    public List<DialColourResponse> getAllDialColours() {

        List<DialColour> dialColours =
                dialColourRepository.findAll();

        return dialColours.stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET BY ID
    public DialColourResponse getDialColourById(Long id) {

        DialColour dialColour =
                dialColourRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Dial colour not found with id: " + id
                                )
                        );

        return convertToResponse(dialColour);
    }

    // UPDATE
    public DialColourResponse updateDialColour(
            Long id,
            DialColourRequest request) {

        DialColour dialColour =
                dialColourRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Dial colour not found with id: " + id
                                )
                        );

        dialColour.setDialColour(request.getDialColour());

        DialColour updatedDialColour =
                dialColourRepository.save(dialColour);

        return convertToResponse(updatedDialColour);
    }

    // DELETE
    public void deleteDialColour(Long id) {

        DialColour dialColour =
                dialColourRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Dial colour not found with id: " + id
                                )
                        );

        dialColourRepository.delete(dialColour);
    }

    // ENTITY → RESPONSE DTO
    private DialColourResponse convertToResponse(
            DialColour dialColour) {

        DialColourResponse response =
                new DialColourResponse();

        response.setDialColourId(
                dialColour.getDialColourId()
        );

        response.setDialColour(
                dialColour.getDialColour()
        );

        response.setCreatedAt(
                dialColour.getCreatedAt()
        );

        response.setUpdatedAt(
                dialColour.getUpdatedAt()
        );

        return response;
    }
}