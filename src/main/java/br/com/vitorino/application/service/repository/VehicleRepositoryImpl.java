package br.com.vitorino.application.service.repository;

import br.com.vitorino.application.service.repository.mapper.VehicleEntityMapper;
import br.com.vitorino.domain.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VehicleRepositoryImpl implements VehicleRepository {

    @Autowired
    IVehicleRepository repository;

    @Autowired
    VehicleEntityMapper mapper;

    @Override
    public Optional<Vehicle> findById(UUID id) {
        final var entity = repository.findById(id);
        return entity.isPresent() ? Optional.of(mapper.entityToModel(entity.get())) : Optional.empty();
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return mapper.entityToModel(repository.save(mapper.modelToEntity(vehicle)));
    }

    @Override
    public List<Vehicle> findAll() {
        return repository.findAll().stream().map(entity -> mapper.entityToModel(entity)).collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }
}
