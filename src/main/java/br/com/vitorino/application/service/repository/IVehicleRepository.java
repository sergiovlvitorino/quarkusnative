package br.com.vitorino.application.service.repository;


import br.com.vitorino.application.service.repository.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IVehicleRepository extends JpaRepository<VehicleEntity, UUID> {
}
