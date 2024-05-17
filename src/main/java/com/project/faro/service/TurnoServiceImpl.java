package com.project.faro.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.faro.config.ConstantConfig;
import com.project.faro.dto.BalanceDto;
import com.project.faro.dto.TurnoDto;
import com.project.faro.dto.converter.BalanceConverter;
import com.project.faro.dto.converter.TurnoConverter;
import com.project.faro.entity.Turno;
import com.project.faro.repository.TurnoRepository;

@Service
public class TurnoServiceImpl implements TurnoService {

	@Autowired
	TurnoConverter turnoConverter;
	
	@Autowired
	TurnoRepository turnoRepository;
	
	@Autowired
	HabitacionService habitacionService;
	
	@Autowired
	MercaderiaService mercaderiaService;
	
	@Autowired
	BalanceConverter balanceConverter;
	
	@Override
	@Transactional
	public TurnoDto createTurno(TurnoDto turnoDto,Integer userCreatedId) throws Exception {
		Turno turno = new Turno();
		if(turnoDto.getHabitacionDto() == null) {
			throw new Exception("Se debe enviar la habitacion del turno");
		}
		
		turnoDto.setDateInit(new Date());
		 //Agrego las horas para saber a que hora finaliza el turno
		//----------------------------------------------------
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //calendar.add(Calendar.HOUR_OF_DAY, turnoDto.getCantHoras()); si queremos cargar a mano las horas
        calendar.add(Calendar.HOUR_OF_DAY, ConstantConfig.CANT_HORA_X_TURNO);
        Date fechaActualizada = calendar.getTime();
        
		turnoDto.setDateFinish(fechaActualizada);
		turnoDto.setUserIdCreacion(userCreatedId);
		//----------------------------------------------------
		turnoDto.setStatus(ConstantConfig.TURNO_STATUS_ACT);
		turnoDto.setPrecioTotal(turnoDto.getHabitacionDto().getPrecio());
		turno = turnoConverter.toEntity(turnoDto);
		
		turnoRepository.save(turno);
		
		//CAMBIO EL ESTADO DE LA HABITACION COMO OCUPADA
		habitacionService.updateHabitacionById(turno.getHabitacion().getId(), ConstantConfig.HABITACION_STATUS_BAJ);
		
		
		return turnoConverter.toDto(turnoRepository.save(turno));
		
	}
	
	@Override
	@Transactional
	public TurnoDto updateTurno(TurnoDto turnoDto, Integer userCloseId) throws Exception {
		Turno turno = turnoRepository.findById(turnoDto.getId()).orElse(null);
		if(turno != null) {
			if(turno.getStatus() != turnoDto.getStatus()) {
				//actualizo la habitacion a estado activo
				habitacionService.updateHabitacionById(turnoDto.getHabitacionDto().getId(), ConstantConfig.HABITACION_STATUS_ACT);
				turno.setStatus(turnoDto.getStatus());
			}
			if(turnoDto.getCantHoras() != null) {
				 // Obtener la fecha de finalización actual
			    Date fechaActualizada = turnoDto.getDateFinish();

			    // Crear un objeto Calendar y establecer la fecha de finalización actual
			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(fechaActualizada);

			    // Agregar las horas especificadas
			    calendar.add(Calendar.HOUR_OF_DAY, turnoDto.getCantHoras());

			    // Obtener la fecha actualizada
			    fechaActualizada = calendar.getTime();

			    // Establecer la fecha de finalización actualizada en el turno
			    turno.setDateFinish(fechaActualizada);
			}
			if(turnoDto.getPrecioTotal() != null) {
				turno.setPrecioTotal(turnoDto.getPrecioTotal());
			}
			if(userCloseId != null) {
				turno.setUserIdCierre(userCloseId);	
			}
					
			turno = turnoRepository.save(turno);
		}else {
			throw new RuntimeException("habitacion no encontrado para el ID proporcionado: " + turnoDto.getId());
		}
		return turnoConverter.toDto(turnoRepository.save(turno));
	}
	
	@Override
	public List<TurnoDto> getActiveTurnoDto() throws Exception {
		List<TurnoDto> list = new ArrayList<>();
		list = turnoConverter.toDtoList(turnoRepository.findTurnoByStatus(ConstantConfig.TURNO_STATUS_ACT));
		return list;
	}
	
	
	@Override
	public BalanceDto getBalanceTurnoDto() throws Exception {
		List<TurnoDto> list = new ArrayList<>();
		return balanceConverter.toDto(turnoRepository.findTurnoByStatus(ConstantConfig.TURNO_STATUS_BAJ));
	}
}
