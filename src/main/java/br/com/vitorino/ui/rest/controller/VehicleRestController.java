package br.com.vitorino.ui.rest.controller;


import br.com.vitorino.application.command.VehicleCommandProcessor;
import br.com.vitorino.domain.model.Vehicle;
import br.com.vitorino.infrastructure.exception.NotFoundException;
import br.com.vitorino.infrastructure.exception.UnprocessableEntityException;
import br.com.vitorino.ui.rest.dto.VehicleResponseDTO;
import br.com.vitorino.ui.rest.dto.VehicleSaveRequestDTO;
import br.com.vitorino.ui.rest.mapper.VehicleMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicle")
public class VehicleRestController {

    @Autowired
    VehicleCommandProcessor commandProcessor;

    @Autowired
    VehicleMapper mapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<VehicleResponseDTO> get() {
        return commandProcessor
                .findAll()
                .parallelStream()
                .map(vehicle -> mapper.modelToResponseDTO(vehicle))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VehicleResponseDTO post(@RequestBody @Valid VehicleSaveRequestDTO requestDTO) throws JsonProcessingException {
        final Vehicle vehicle = commandProcessor.save(mapper.saveRequestDTOToModel(requestDTO));
        if (vehicle != null)
            return mapper.modelToResponseDTO(vehicle);
        throw new UnprocessableEntityException("Item was not created");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public String delete() {
        commandProcessor.deleteAll();
        return "{\"result\":\"Items were deleted\"}";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public VehicleResponseDTO getById(@PathVariable UUID id) {
        return mapper.
                modelToResponseDTO(
                        commandProcessor
                                .findById(id)
                                .orElseThrow(() -> new NotFoundException("Item was not found")));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public String deleteById(@PathVariable UUID id) {
        commandProcessor.deleteById(id);
        return "{\"result\":\"Item was deleted\"}";
    }

}
