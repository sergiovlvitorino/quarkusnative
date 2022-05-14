package br.com.vitorino.application.service.repository;

import br.com.vitorino.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository {

    Optional<Vehicle> findById(UUID id);

    Vehicle save(Vehicle vehicle);

    List<Vehicle> findAll();

    void deleteAll();

    void deleteById(UUID id);

    boolean existsById(UUID id);
}
