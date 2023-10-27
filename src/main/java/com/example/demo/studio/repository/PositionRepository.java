package com.example.demo.studio.repository;

import com.example.demo.studio.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByAddressIgnoreCase(String address);
    List<Position> findByStudio_Id(Long studioId);

}
