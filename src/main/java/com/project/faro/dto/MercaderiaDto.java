package com.project.faro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MercaderiaDto {

	private Integer id;
	private String nombre;
	private Integer cantidad;
	private Double precio;
}
