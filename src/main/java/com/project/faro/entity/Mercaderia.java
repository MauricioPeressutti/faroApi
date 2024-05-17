package com.project.faro.entity;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity(name = "mercaderia")
public class Mercaderia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Integer cantidad;
	private Double precio;
	
	 @OneToMany(mappedBy = "mercaderia")
	    private List<MercaderiaPorTurno> mercaderiasPorTurno;
	
	public Mercaderia(Integer id, String nombre, Integer cantidad, Double precio) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	
}
