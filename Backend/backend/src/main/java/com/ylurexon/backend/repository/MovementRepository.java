package com.ylurexon.backend.repository;
import com.ylurexon.backend.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  MovementRepository  extends JpaRepository<Movement,Long> {
}
