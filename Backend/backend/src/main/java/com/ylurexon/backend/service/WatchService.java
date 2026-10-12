package com.ylurexon.backend.service;

import com.ylurexon.backend.dto.PageResponse;
import com.ylurexon.backend.dto.WatchRequest;
import com.ylurexon.backend.dto.WatchResponse;
import com.ylurexon.backend.entity.*;
import com.ylurexon.backend.exception.ResourceNotFoundException;
import com.ylurexon.backend.repository.*;
import com.ylurexon.backend.specification.WatchSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WatchService {

    private final WatchRepository watchRepository;
    private final BrandRepository brandRepository;
    private final MovementRepository movementRepository;
    private final StrapMaterialRepository strapMaterialRepository;
    private final GlassTypeRepository glassTypeRepository;
    private final DialColourRepository dialColourRepository;
    private final CaseMaterialRepository caseMaterialRepository;
    private final CaseSizeRepository caseSizeRepository;

    public WatchService(
            WatchRepository watchRepository,
            BrandRepository brandRepository,
            MovementRepository movementRepository,
            StrapMaterialRepository strapMaterialRepository,
            GlassTypeRepository glassTypeRepository,
            DialColourRepository dialColourRepository,
            CaseMaterialRepository caseMaterialRepository,
            CaseSizeRepository caseSizeRepository
    ) {
        this.watchRepository = watchRepository;
        this.brandRepository = brandRepository;
        this.movementRepository = movementRepository;
        this.strapMaterialRepository = strapMaterialRepository;
        this.glassTypeRepository = glassTypeRepository;
        this.dialColourRepository = dialColourRepository;
        this.caseMaterialRepository = caseMaterialRepository;
        this.caseSizeRepository = caseSizeRepository;
    }

    // CREATE
    public WatchResponse createWatch(WatchRequest request) {

        Watch watch = new Watch();

        watch.setWatchName(request.getWatchName());

        watch.setBrand(
                brandRepository.findById(request.getBrandId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Brand not found with id: "
                                                + request.getBrandId()))
        );

        watch.setMovement(
                movementRepository.findById(request.getMovementId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Movement not found with id: "
                                                + request.getMovementId()))
        );

        watch.setStrapMaterial(
                strapMaterialRepository.findById(
                                request.getStrapMaterialId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Strap material not found with id: "
                                                + request.getStrapMaterialId()))
        );

        watch.setGlassType(
                glassTypeRepository.findById(
                                request.getGlassTypeId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Glass type not found with id: "
                                                + request.getGlassTypeId()))
        );

        watch.setDialColour(
                dialColourRepository.findById(
                                request.getDialColourId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Dial colour not found with id: "
                                                + request.getDialColourId()))
        );

        watch.setCaseMaterial(
                caseMaterialRepository.findById(
                                request.getCaseMaterialId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Case material not found with id: "
                                                + request.getCaseMaterialId()))
        );

        watch.setCaseSize(
                caseSizeRepository.findById(
                                request.getCaseSizeId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Case size not found with id: "
                                                + request.getCaseSizeId()))
        );

        watch.setPrice(request.getPrice());
        watch.setDescription(request.getDescription());
        watch.setWaterResistance(request.getWaterResistance());
        watch.setPowerReserve(request.getPowerReserve());
        watch.setReleaseYear(request.getReleaseYear());
        watch.setImageUrl(request.getImageUrl());

        Watch savedWatch = watchRepository.save(watch);

        return convertToResponse(savedWatch);
    }

    // GET ALL / SEARCH / FILTER / SORT / PAGINATION
    public PageResponse<WatchResponse> getAllWatches(
            String search,
            Long brandId,
            Long movementId,
            Long strapMaterialId,
            Long glassTypeId,
            Long dialColourId,
            Long caseMaterialId,
            Long caseSizeId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Integer minReleaseYear,
            Integer maxReleaseYear,
            String sort,
            int page,
            int size) {

        Specification<Watch> specification =
                Specification.where(null);

        // SEARCH
        if (search != null && !search.isBlank()) {

            specification = specification.and(
                    WatchSpecification.search(search)
            );
        }

        // BRAND
        if (brandId != null) {

            specification = specification.and(
                    WatchSpecification.hasBrand(brandId)
            );
        }

        // MOVEMENT
        if (movementId != null) {

            specification = specification.and(
                    WatchSpecification.hasMovement(movementId)
            );
        }

        // STRAP MATERIAL
        if (strapMaterialId != null) {

            specification = specification.and(
                    WatchSpecification.hasStrapMaterial(
                            strapMaterialId
                    )
            );
        }

        // GLASS TYPE
        if (glassTypeId != null) {

            specification = specification.and(
                    WatchSpecification.hasGlassType(
                            glassTypeId
                    )
            );
        }

        // DIAL COLOUR
        if (dialColourId != null) {

            specification = specification.and(
                    WatchSpecification.hasDialColour(
                            dialColourId
                    )
            );
        }

        // CASE MATERIAL
        if (caseMaterialId != null) {

            specification = specification.and(
                    WatchSpecification.hasCaseMaterial(
                            caseMaterialId
                    )
            );
        }

        // CASE SIZE
        if (caseSizeId != null) {

            specification = specification.and(
                    WatchSpecification.hasCaseSize(
                            caseSizeId
                    )
            );
        }

        // MINIMUM PRICE
        if (minPrice != null) {

            specification = specification.and(
                    WatchSpecification.priceGreaterThanOrEqual(
                            minPrice
                    )
            );
        }

        // MAXIMUM PRICE
        if (maxPrice != null) {

            specification = specification.and(
                    WatchSpecification.priceLessThanOrEqual(
                            maxPrice
                    )
            );
        }

        // MINIMUM RELEASE YEAR
        if (minReleaseYear != null) {

            specification = specification.and(
                    WatchSpecification.releaseYearGreaterThanOrEqual(
                            minReleaseYear
                    )
            );
        }

        // MAXIMUM RELEASE YEAR
        if (maxReleaseYear != null) {

            specification = specification.and(
                    WatchSpecification.releaseYearLessThanOrEqual(
                            maxReleaseYear
                    )
            );
        }

        // SORTING
        Sort sorting;

        switch (sort) {

            case "price_asc":
                sorting = Sort.by(
                        Sort.Direction.ASC,
                        "price"
                );
                break;

            case "price_desc":
                sorting = Sort.by(
                        Sort.Direction.DESC,
                        "price"
                );
                break;

            case "name_desc":
                sorting = Sort.by(
                        Sort.Direction.DESC,
                        "watchName"
                );
                break;

            case "newest":
                sorting = Sort.by(
                        Sort.Direction.DESC,
                        "releaseYear"
                );
                break;

            case "oldest":
                sorting = Sort.by(
                        Sort.Direction.ASC,
                        "releaseYear"
                );
                break;

            case "name_asc":
            default:
                sorting = Sort.by(
                        Sort.Direction.ASC,
                        "watchName"
                );
                break;
        }

        // PAGINATION
        Pageable pageable =
                PageRequest.of(page, size, sorting);

        Page<Watch> watchPage =
                watchRepository.findAll(
                        specification,
                        pageable
                );

        // CONVERT WATCH ENTITIES TO RESPONSE DTOs
        List<WatchResponse> watchResponses =
                watchPage.getContent()
                        .stream()
                        .map(this::convertToResponse)
                        .toList();

        // RETURN PAGINATED RESPONSE
        return new PageResponse<>(
                watchResponses,
                watchPage.getNumber(),
                watchPage.getSize(),
                watchPage.getTotalElements(),
                watchPage.getTotalPages(),
                watchPage.isLast()
        );
    }

    // GET BY ID
    public WatchResponse getWatchById(Long id) {

        Watch watch = watchRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Watch not found with id: " + id
                        )
                );

        return convertToResponse(watch);
    }

    // UPDATE
    public WatchResponse updateWatch(
            Long id,
            WatchRequest request) {

        Watch watch = watchRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Watch not found with id: " + id
                        )
                );

        watch.setWatchName(request.getWatchName());

        watch.setBrand(
                brandRepository.findById(request.getBrandId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Brand not found with id: "
                                                + request.getBrandId()))
        );

        watch.setMovement(
                movementRepository.findById(request.getMovementId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Movement not found with id: "
                                                + request.getMovementId()))
        );

        watch.setStrapMaterial(
                strapMaterialRepository.findById(
                                request.getStrapMaterialId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Strap material not found with id: "
                                                + request.getStrapMaterialId()))
        );

        watch.setGlassType(
                glassTypeRepository.findById(
                                request.getGlassTypeId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Glass type not found with id: "
                                                + request.getGlassTypeId()))
        );

        watch.setDialColour(
                dialColourRepository.findById(
                                request.getDialColourId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Dial colour not found with id: "
                                                + request.getDialColourId()))
        );

        watch.setCaseMaterial(
                caseMaterialRepository.findById(
                                request.getCaseMaterialId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Case material not found with id: "
                                                + request.getCaseMaterialId()))
        );

        watch.setCaseSize(
                caseSizeRepository.findById(
                                request.getCaseSizeId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Case size not found with id: "
                                                + request.getCaseSizeId()))
        );

        watch.setPrice(request.getPrice());
        watch.setDescription(request.getDescription());
        watch.setWaterResistance(request.getWaterResistance());
        watch.setPowerReserve(request.getPowerReserve());
        watch.setReleaseYear(request.getReleaseYear());
        watch.setImageUrl(request.getImageUrl());

        Watch updatedWatch = watchRepository.save(watch);

        return convertToResponse(updatedWatch);
    }

    // DELETE
    public void deleteWatch(Long id) {

        Watch watch = watchRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Watch not found with id: " + id
                        )
                );

        watchRepository.delete(watch);
    }

    // ENTITY → RESPONSE DTO
    private WatchResponse convertToResponse(Watch watch) {

        WatchResponse response = new WatchResponse();

        response.setWatchId(watch.getWatchId());
        response.setWatchName(watch.getWatchName());

        response.setBrandId(
                watch.getBrand().getBrandId()
        );

        response.setMovementId(
                watch.getMovement().getMovementId()
        );

        response.setStrapMaterialId(
                watch.getStrapMaterial().getStrapMaterialId()
        );

        response.setGlassTypeId(
                watch.getGlassType().getGlassTypeId()
        );

        response.setDialColourId(
                watch.getDialColour().getDialColourId()
        );

        response.setCaseMaterialId(
                watch.getCaseMaterial().getCaseId()
        );

        response.setCaseSizeId(
                watch.getCaseSize().getCaseSizeId()
        );

        response.setPrice(watch.getPrice());
        response.setDescription(watch.getDescription());
        response.setWaterResistance(
                watch.getWaterResistance()
        );
        response.setPowerReserve(
                watch.getPowerReserve()
        );
        response.setReleaseYear(
                watch.getReleaseYear()
        );
        response.setImageUrl(watch.getImageUrl());

        response.setCreatedAt(watch.getCreatedAt());
        response.setUpdatedAt(watch.getUpdatedAt());

        return response;
    }
}