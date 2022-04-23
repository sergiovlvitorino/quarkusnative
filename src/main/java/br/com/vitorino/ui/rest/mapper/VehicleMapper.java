package br.com.vitorino.ui.rest.mapper;

import br.com.vitorino.domain.model.Vehicle;
import br.com.vitorino.ui.rest.dto.VehicleListResponseDTO;
import br.com.vitorino.ui.rest.dto.VehicleSaveRequestDTO;
import br.com.vitorino.ui.rest.dto.VehicleSaveResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {
    public Vehicle saveRequestDTOToModel(final VehicleSaveRequestDTO requestDTO) {
        final Vehicle vehicle = new Vehicle();
        vehicle.setName(requestDTO.getName());
        return vehicle;
    }

    public VehicleSaveResponseDTO modelToSaveResponseDTO(final Vehicle vehicle) {
        final VehicleSaveResponseDTO responseDTO = new VehicleSaveResponseDTO();
        responseDTO.setId(vehicle.getId());
        return responseDTO;
    }

    public VehicleListResponseDTO modelToListResponseDTO(final Vehicle vehicle) {
        final VehicleListResponseDTO responseDTO = new VehicleListResponseDTO();
        responseDTO.setId(vehicle.getId());
        responseDTO.setName(vehicle.getName());
        return responseDTO;
    }

}
