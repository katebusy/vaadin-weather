package org.example.application.repositories;

import org.example.application.entities.WeatherRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherRequest, UUID> {
}
