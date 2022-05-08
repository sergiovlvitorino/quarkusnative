package br.com.vitorino.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Vehicle {

    @Id
    private UUID id;

    private String name;

}
