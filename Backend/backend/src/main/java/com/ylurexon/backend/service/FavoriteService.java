package com.ylurexon.backend.service;

import com.ylurexon.backend.dto.FavoriteResponse;
import com.ylurexon.backend.entity.Favorite;
import com.ylurexon.backend.entity.User;
import com.ylurexon.backend.entity.Watch;
import com.ylurexon.backend.exception.DuplicateResourceException;
import com.ylurexon.backend.exception.ResourceNotFoundException;
import com.ylurexon.backend.repository.FavoriteRepository;
import com.ylurexon.backend.repository.UserRepository;
import com.ylurexon.backend.repository.WatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final WatchRepository watchRepository;

    public FavoriteService(
            FavoriteRepository favoriteRepository,
            UserRepository userRepository,
            WatchRepository watchRepository) {

        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.watchRepository = watchRepository;
    }

    // ADD FAVORITE
    public void addFavorite(
            String email,
            Long watchId) {

        User user = findUserByEmail(email);

        Watch watch = watchRepository.findById(watchId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Watch not found with id: " + watchId
                        )
                );

        if (favoriteRepository
                .findByUserAndWatch(user, watch)
                .isPresent()) {

            throw new DuplicateResourceException(
                    "Watch is already in favorites"
            );
        }

        Favorite favorite = new Favorite();

        favorite.setUser(user);
        favorite.setWatch(watch);

        favoriteRepository.save(favorite);
    }

    // REMOVE FAVORITE
    public void removeFavorite(
            String email,
            Long watchId) {

        User user = findUserByEmail(email);

        Watch watch = watchRepository.findById(watchId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Watch not found with id: " + watchId
                        )
                );

        Favorite favorite = favoriteRepository
                .findByUserAndWatch(user, watch)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Watch is not in favorites"
                        )
                );

        favoriteRepository.delete(favorite);
    }

    // GET USER FAVORITES
    public List<FavoriteResponse> getFavorites(
            String email) {

        User user = findUserByEmail(email);

        List<Favorite> favorites =
                favoriteRepository.findByUser(user);

        return favorites.stream()
                .map(favorite -> {

                    Watch watch = favorite.getWatch();

                    return new FavoriteResponse(
                            watch.getWatchId(),
                            watch.getWatchName(),
                            watch.getPrice(),
                            watch.getImageUrl()
                    );
                })
                .toList();
    }

    // FIND USER
    private User findUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );
    }
}