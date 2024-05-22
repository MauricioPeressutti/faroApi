package com.project.faro.service;

import java.util.List;

import com.project.faro.dto.BalanceDto;
import com.project.faro.dto.TurnoDto;

public interface TurnoService {

	TurnoDto createTurno(TurnoDto turnoDto, Integer userCreatedId) throws Exception;

	List<TurnoDto> getActiveTurnoDto() throws Exception;
	
	BalanceDto getBalanceTurnoDto() throws Exception;

	TurnoDto updateTurno(TurnoDto turnoDto, Integer userCloseId) throws Exception;

	BalanceDto getBalanceForDayTurnoDto(int cantOfDays) throws Exception;
	

}
