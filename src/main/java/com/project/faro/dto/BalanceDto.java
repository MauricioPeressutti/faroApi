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
public class BalanceDto {
	
	private Double totalAcumulado;
	private Integer cantTurnos;
	private List<TurnoDto> turnoList;
}
