package br.com.vitorino.application.service.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
public class VehicleEntity {

    @Id
    private UUID id;

    private String name;

}
