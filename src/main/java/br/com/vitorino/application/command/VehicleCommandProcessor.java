package br.com.vitorino.application.command;

import br.com.vitorino.application.service.repository.VehicleRepository;
import br.com.vitorino.application.visitor.GenerateIdVisitor;
import br.com.vitorino.domain.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class VehicleCommandProcessor {

    @Autowired
    VehicleRepository repository;
    @Autowired
    GenerateIdVisitor generateIdVisitor;

    public Vehicle save(final Vehicle vehicle) {
        vehicle.receive(generateIdVisitor);
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
        if (existsById(id))
            repository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}
