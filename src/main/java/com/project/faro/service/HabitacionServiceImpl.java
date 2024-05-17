package com.project.faro.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.faro.config.ConstantConfig;
import com.project.faro.dto.HabitacionDto;
import com.project.faro.dto.converter.HabitacionConverter;
import com.project.faro.entity.Habitacion;
import com.project.faro.repository.HabitacionRepository;

@Service
public class HabitacionServiceImpl implements HabitacionService {
	
	@Autowired
	HabitacionRepository habitacionRepository;
	
	@Autowired
	HabitacionConverter habitacionConverter;
	
	@Override
	public List<HabitacionDto> getAllHabitacionDto() throws Exception {
		return habitacionConverter.toDtoList(habitacionRepository.findAll());
	}
	
	@Override
	public List<HabitacionDto> getActiveHabitacionDto() throws Exception {
		return habitacionConverter.toDtoList(habitacionRepository.findHabitacionByStatus(ConstantConfig.HABITACION_STATUS_ACT));
	}
	
	@Override
	public HabitacionDto getHabitacionDtoById(Integer id) throws Exception {
		Habitacion habitacion = habitacionRepository.findById(id)
	            .orElseThrow(() -> new Exception("Habitacion no encontrada para el ID proporcionado: " + id));
	    return habitacionConverter.toDto(habitacion);
	}
	
	@Override
	public void updateHabitacionById(Integer id, String status) throws Exception {
		Habitacion habitacion = habitacionRepository.findById(id).orElse(null);
		if(habitacion != null) {
			habitacion.setStatus(status);
			habitacionRepository.save(habitacion);
		}else {
			throw new RuntimeException("habitacion no encontrado para el ID proporcionado: " + id);
		}
	}
}
