package br.com.vitorino.ui.rest.mapper;

import br.com.vitorino.domain.model.Vehicle;
import br.com.vitorino.ui.rest.dto.VehicleResponseDTO;
import br.com.vitorino.ui.rest.dto.VehicleSaveRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VehicleMapper {

    @Mapping(source = "name", target = "name")
    Vehicle saveRequestDTOToModel(final VehicleSaveRequestDTO requestDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    VehicleResponseDTO modelToResponseDTO(final Vehicle vehicle);

}
