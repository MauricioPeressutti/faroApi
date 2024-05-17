package com.project.faro.dto.converter;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.faro.dto.HabitacionDto;
import com.project.faro.dto.PersonDto;
import com.project.faro.entity.Habitacion;
import com.project.faro.entity.Person;

@Service
public class HabitacionConverter {
	public HabitacionDto toDto(Habitacion habitacion) {
		HabitacionDto dto = new HabitacionDto();
		if (habitacion != null) {
			dto.setId(habitacion.getId());
			dto.setNombre(habitacion.getNombre());
			dto.setPrecio(habitacion.getPrecio());
			dto.setStatus(habitacion.getStatus());
		}
		return dto;
	}

	public Habitacion toEntity(HabitacionDto dto) {
		Habitacion entity = new Habitacion();
		if (dto != null) {
			entity.setId(dto.getId());
			entity.setNombre(dto.getNombre());
			entity.setPrecio(dto.getPrecio());
			entity.setStatus(dto.getStatus());
		}
		return entity;
	}

	public List<HabitacionDto> toDtoList(List<Habitacion> entities) throws Exception {
		List<HabitacionDto> result = new ArrayList<>();
		for (Habitacion entityPerson : entities) {
			result.add(toDto(entityPerson));
		}
		return result;
	}
}
