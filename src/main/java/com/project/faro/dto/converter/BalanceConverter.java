package com.project.faro.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.faro.dto.BalanceDto;
import com.project.faro.dto.TurnoDto;
import com.project.faro.entity.Turno;

@Service
public class BalanceConverter {
	
	@Autowired
	TurnoConverter turnoConverter;
	
	public BalanceDto toDto(List<Turno> list) throws Exception {
		BalanceDto dto = new BalanceDto();
		List<TurnoDto> turnoList = new ArrayList();
		Double cant = 0D;
		for (Turno t : list) {
			turnoList.add(turnoConverter.toDto(t));
			cant += t.getPrecioTotal();
		}
		dto.setTurnoList(turnoList);
		dto.setCantTurnos(list.size());
		dto.setTotalAcumulado(cant);
		/*HabitacionDto dto = new HabitacionDto();
		if (habitacion != null) {
			dto.setId(habitacion.getId());
			dto.setNombre(habitacion.getNombre());
			dto.setPrecio(habitacion.getPrecio());
			dto.setStatus(habitacion.getStatus());
		}*/
		return dto;
		}
}
