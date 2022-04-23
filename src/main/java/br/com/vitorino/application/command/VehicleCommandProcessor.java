package br.com.vitorino.application.command;

import br.com.vitorino.domain.model.Vehicle;
import br.com.vitorino.domain.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class VehicleCommandProcessor {

    @Autowired
    private VehicleRepository repository;

    public Vehicle save(final Vehicle vehicle) {
        vehicle.setId(UUID.randomUUID());
        return repository.save(vehicle);
    }

    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Optional<Vehicle> findById(UUID id) {
        return repository.findById(id);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
