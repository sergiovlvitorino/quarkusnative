package br.com.vitorino.ui.rest.controller;


import br.com.vitorino.application.command.VehicleCommandProcessor;
import br.com.vitorino.domain.model.Vehicle;
import br.com.vitorino.ui.rest.dto.VehicleListResponseDTO;
import br.com.vitorino.ui.rest.dto.VehicleSaveRequestDTO;
import br.com.vitorino.ui.rest.dto.VehicleSaveResponseDTO;
import br.com.vitorino.ui.rest.mapper.VehicleMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicle")
public class VehicleRestController {

    private static final Logger log = LoggerFactory.getLogger(VehicleRestController.class);

    @Autowired
    private VehicleMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VehicleCommandProcessor commandProcessor;

    @GetMapping
    public ResponseEntity get() {
        final List<VehicleListResponseDTO> vehicleListResponseDTOList = commandProcessor
                .findAll()
                .parallelStream()
                .map(vehicle -> mapper.modelToListResponseDTO(vehicle))
                .collect(Collectors.toList());
        return ResponseEntity
                .ok()
                .body(vehicleListResponseDTOList);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody VehicleSaveRequestDTO requestDTO) throws JsonProcessingException {
        log.info(String.format("{\"msg\":\"VehicleSaveRequestDTO received\", \"value\":%s}", objectMapper.writeValueAsString(requestDTO)));

        final Vehicle vehicle = commandProcessor.save(mapper.saveRequestDTOToModel(requestDTO));
        if (vehicle != null)
            log.info(String.format("{\"msg\":\"Vehicle created\", \"value\":%s}", objectMapper.writeValueAsString(vehicle)));
        else
            log.info(String.format("{\"msg\":\"Vehicle not created\", \"value\":%s}", objectMapper.writeValueAsString(vehicle)));

        final VehicleSaveResponseDTO responseDTO = mapper.modelToSaveResponseDTO(vehicle);
        log.info(String.format("{\"msg\":\"VehicleSaveResponseDTO created\", \"value\":%s}", objectMapper.writeValueAsString(responseDTO)));

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping
    public ResponseEntity delete() {
        commandProcessor.deleteAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable UUID id) {
        final Optional<Vehicle> optionalVehicle = commandProcessor.findById(id);
        if (optionalVehicle.isPresent())
            return ResponseEntity.ok(mapper.modelToListResponseDTO(optionalVehicle.get()));
        else
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable UUID id) {
        commandProcessor.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
