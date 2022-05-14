package br.com.vitorino.application.visitor;

import br.com.vitorino.domain.model.Vehicle;
import br.com.vitorino.infrastructure.visitor.Visitor;

import java.util.UUID;

public interface GenerateIdVisitor extends Visitor<Vehicle, UUID> {

}
