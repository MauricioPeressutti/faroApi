package com.project.faro.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.project.faro.util.RegistroSerializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "turno")
public class Turno implements RegistroSerializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String patente;
	@NotNull
	private Date dateInit;
	private Date dateFinish;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "habitacion_id", columnDefinition = "int not null")
	private Habitacion habitacion;
	@Column(columnDefinition = "varchar(3)")
	private String status;
	private Double precioTotal;
	
	 // Columnas para el usuario que creó y cerró el registro
    @Column(name = "user_id_creacion")
    private Integer userIdCreacion;

    @Column(name = "user_id_cierre")
    private Integer userIdCierre;
}
