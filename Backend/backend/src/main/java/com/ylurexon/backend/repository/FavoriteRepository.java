package com.ylurexon.backend.repository;

import com.ylurexon.backend.entity.Favorite;
import com.ylurexon.backend.entity.User;
import com.ylurexon.backend.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository
        extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByUserAndWatch(
            User user,
            Watch watch
    );

    List<Favorite> findByUser(User user);

    void deleteByUserAndWatch(
            User user,
            Watch watch
    );
}