package com.ylurexon.backend.specification;

import com.ylurexon.backend.entity.Watch;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class WatchSpecification {

    // SEARCH BY WATCH NAME OR DESCRIPTION
    public static Specification<Watch> search(String search) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(
                                        root.get("watchName")
                                ),
                                "%" + search.toLowerCase() + "%"
                        ),
                        criteriaBuilder.like(
                                criteriaBuilder.lower(
                                        root.get("description")
                                ),
                                "%" + search.toLowerCase() + "%"
                        )
                );
    }

    // FILTER BY BRAND
    public static Specification<Watch> hasBrand(Long brandId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("brand").get("brandId"),
                        brandId
                );
    }

    // FILTER BY MOVEMENT
    public static Specification<Watch> hasMovement(Long movementId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("movement").get("movementId"),
                        movementId
                );
    }

    // FILTER BY STRAP MATERIAL
    public static Specification<Watch> hasStrapMaterial(
            Long strapMaterialId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("strapMaterial")
                                .get("strapMaterialId"),
                        strapMaterialId
                );
    }

    // FILTER BY GLASS TYPE
    public static Specification<Watch> hasGlassType(
            Long glassTypeId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("glassType")
                                .get("glassTypeId"),
                        glassTypeId
                );
    }

    // FILTER BY DIAL COLOUR
    public static Specification<Watch> hasDialColour(
            Long dialColourId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("dialColour")
                                .get("dialColourId"),
                        dialColourId
                );
    }

    // FILTER BY CASE MATERIAL
    public static Specification<Watch> hasCaseMaterial(
            Long caseMaterialId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("caseMaterial")
                                .get("caseId"),
                        caseMaterialId
                );
    }

    // FILTER BY CASE SIZE
    public static Specification<Watch> hasCaseSize(
            Long caseSizeId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("caseSize")
                                .get("caseSizeId"),
                        caseSizeId
                );
    }

    // FILTER BY MINIMUM PRICE
    public static Specification<Watch> priceGreaterThanOrEqual(
            BigDecimal minPrice) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("price"),
                        minPrice
                );
    }

    // FILTER BY MAXIMUM PRICE
    public static Specification<Watch> priceLessThanOrEqual(
            BigDecimal maxPrice) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(
                        root.get("price"),
                        maxPrice
                );
    }

    // FILTER BY MINIMUM RELEASE YEAR
    public static Specification<Watch> releaseYearGreaterThanOrEqual(
            Integer minReleaseYear) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("releaseYear"),
                        minReleaseYear
                );
    }

    // FILTER BY MAXIMUM RELEASE YEAR
    public static Specification<Watch> releaseYearLessThanOrEqual(
            Integer maxReleaseYear) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(
                        root.get("releaseYear"),
                        maxReleaseYear
                );
    }
}