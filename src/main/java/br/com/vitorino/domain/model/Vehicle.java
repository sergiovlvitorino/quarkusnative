package br.com.vitorino.domain.model;

import br.com.vitorino.infrastructure.visitor.Host;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Vehicle implements Host {

    private UUID id;

    private String name;

}
