package com.project.faro.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.faro.dto.HabitacionDto;
import com.project.faro.dto.MercaderiaDto;
import com.project.faro.dto.PersonDto;
import com.project.faro.entity.Habitacion;
import com.project.faro.entity.Mercaderia;
import com.project.faro.entity.Person;


@Service
public class MercaderiaConverter {
	public MercaderiaDto toDto(Mercaderia mercaderia) {
		MercaderiaDto dto = new MercaderiaDto();
		if (mercaderia != null) {
			dto.setId(mercaderia.getId());
			dto.setNombre(mercaderia.getNombre());
			dto.setPrecio(mercaderia.getPrecio());
			dto.setCantidad(mercaderia.getCantidad());
		}
		return dto;
	}

	public Mercaderia toEntity(MercaderiaDto dto) {
		Mercaderia entity = new Mercaderia();
		if (dto != null) {
			entity.setId(dto.getId());
			entity.setNombre(dto.getNombre());
			entity.setPrecio(dto.getPrecio());
			entity.setCantidad(dto.getCantidad());
			
		}
		return entity;
	}

	public List<MercaderiaDto> toDtoList(List<Mercaderia> entities) throws Exception {
		List<MercaderiaDto> result = new ArrayList<>();
		for (Mercaderia entityPerson : entities) {
			result.add(toDto(entityPerson));
		}
		return result;
	}
}
