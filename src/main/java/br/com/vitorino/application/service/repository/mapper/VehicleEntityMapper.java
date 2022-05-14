package br.com.vitorino.application.service.repository.mapper;


import br.com.vitorino.application.service.repository.entity.VehicleEntity;
import br.com.vitorino.domain.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface VehicleEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    VehicleEntity modelToEntity(Vehicle vehicle);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Vehicle entityToModel(VehicleEntity entity);


}
