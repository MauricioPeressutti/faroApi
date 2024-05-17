package com.project.faro.service;

import java.util.List;

import com.project.faro.dto.HabitacionDto;

public interface HabitacionService {

	List<HabitacionDto> getAllHabitacionDto() throws Exception;

	List<HabitacionDto> getActiveHabitacionDto() throws Exception;

	HabitacionDto getHabitacionDtoById(Integer id) throws Exception;
	
	//UTILIZAR PARA CAMBIAR DE ESTADO LAS HABITACIONES.
	void updateHabitacionById(Integer id, String status) throws Exception;

}
