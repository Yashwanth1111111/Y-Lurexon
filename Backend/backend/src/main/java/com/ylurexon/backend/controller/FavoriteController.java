package com.ylurexon.backend.controller;

import com.ylurexon.backend.dto.FavoriteResponse;
import com.ylurexon.backend.service.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(
            FavoriteService favoriteService) {

        this.favoriteService = favoriteService;
    }

    // ADD FAVORITE
    @PostMapping("/{watchId}")
    public ResponseEntity<Void> addFavorite(
            @PathVariable Long watchId,
            Authentication authentication) {

        String email = authentication.getName();

        favoriteService.addFavorite(email, watchId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    // REMOVE FAVORITE
    @DeleteMapping("/{watchId}")
    public ResponseEntity<Void> removeFavorite(
            @PathVariable Long watchId,
            Authentication authentication) {

        String email = authentication.getName();

        favoriteService.removeFavorite(email, watchId);

        return ResponseEntity
                .noContent()
                .build();
    }

    // GET MY FAVORITES
    @GetMapping
    public ResponseEntity<List<FavoriteResponse>> getFavorites(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                favoriteService.getFavorites(email)
        );
    }
}