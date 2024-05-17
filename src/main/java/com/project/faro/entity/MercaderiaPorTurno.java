package com.project.faro.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mercaderiaPorTurno")
public class MercaderiaPorTurno {
	//En esta entidad se va a ir cargando la mercaderia que se pidio en el turno.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer turnoId;
	private Double precio;
	
	@ManyToOne
    @JoinColumn(name = "mercaderia_id")
    private Mercaderia mercaderia;
}
