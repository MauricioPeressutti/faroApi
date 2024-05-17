package com.project.faro.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurnoDto {
	
	private Integer id;
	private String patente;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date dateInit;
	private Date dateFinish;
	private Integer cantHoras;
	private HabitacionDto habitacionDto;
	private String status;
	private Double precioTotal;
	private List<MercaderiaDto> mercaderiaList;
	private Integer userIdCreacion;
	private Integer userIdCierre;
	private PersonDto personInitTurn;
	private PersonDto personEndTurn;
}
