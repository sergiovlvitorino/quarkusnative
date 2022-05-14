package br.com.vitorino.application.visitor;

import br.com.vitorino.domain.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateIdVisitorImpl implements GenerateIdVisitor {
    @Override
    public UUID visit(Vehicle vehicle) {
        vehicle.setId(UUID.randomUUID());
        return vehicle.getId();
    }
}
