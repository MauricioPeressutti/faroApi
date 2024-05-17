package com.project.faro.dto.converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.faro.dto.HabitacionDto;
import com.project.faro.dto.MercaderiaDto;
import com.project.faro.dto.TurnoDto;
import com.project.faro.entity.Habitacion;
import com.project.faro.entity.MercaderiaPorTurno;
import com.project.faro.entity.Turno;
import com.project.faro.service.HabitacionService;
import com.project.faro.service.MercaderiaPorTurnoService;
import com.project.faro.service.MercaderiaService;
import com.project.faro.service.PersonService;

@Service
public class TurnoConverter {
	
	@Autowired
    HabitacionConverter habitacionConverter;
	
	@Autowired
    HabitacionService habitacionService;
	
	@Autowired
    MercaderiaService mercaderiaService;
	
	@Autowired
    MercaderiaConverter mercaderiaConverter;
	
	@Autowired
	MercaderiaPorTurnoService mercaderiaPorTurnoService;
	
	@Autowired
    PersonConverter personConverter;
	
	@Autowired
    PersonService personService;
	
	
	public TurnoDto toDto(Turno entity) throws Exception {
		TurnoDto dto = new TurnoDto();
		List<MercaderiaDto> list = new ArrayList<>();
		if (entity != null) {
			dto.setId(entity.getId());
			dto.setPatente(entity.getPatente());
			dto.setDateInit(entity.getDateInit());
			dto.setDateFinish(entity.getDateFinish());
			dto.setStatus(entity.getStatus());
			dto.setPrecioTotal(entity.getPrecioTotal());
			if(entity.getHabitacion() != null) {
				dto.setHabitacionDto(habitacionConverter.toDto(entity.getHabitacion()));
			}
			
			//Inicio y fin de turno
			if(entity.getUserIdCreacion() != null) {
				dto.setPersonInitTurn(personConverter.toDto(personService.getPersonByIdEntity(entity.getUserIdCreacion())));
			}
			if(entity.getUserIdCierre() != null) {
				dto.setPersonEndTurn(personConverter.toDto(personService.getPersonByIdEntity(entity.getUserIdCierre())));
			}
			//OBTENGO LAS mercaderia por ID de turno.
			List <MercaderiaPorTurno> mercaderiaList = mercaderiaPorTurnoService.getMercaderiaByTurnoId(entity.getId());
			for(MercaderiaPorTurno mercaderia : mercaderiaList) {
				MercaderiaDto mercDto = new MercaderiaDto(mercaderia.getMercaderia().getId(), mercaderia.getMercaderia().getNombre(), null, 
						mercaderia.getPrecio());
				list.add(mercDto);
			}
			dto.setMercaderiaList(list);
		}
		return dto;
	}

	public Turno toEntity(TurnoDto dto) throws Exception {
		Turno entity = new Turno();
		if (dto != null) {
			entity.setId(dto.getId());
			entity.setPatente(dto.getPatente());
			entity.setDateInit(dto.getDateInit());
			entity.setDateFinish(dto.getDateFinish());
			entity.setStatus(dto.getStatus());
			entity.setPrecioTotal(dto.getPrecioTotal());
			entity.setUserIdCreacion(dto.getUserIdCreacion());
			entity.setUserIdCierre(dto.getUserIdCierre());
			if(dto.getHabitacionDto() != null){
                entity.setHabitacion(new Habitacion());
                HabitacionDto habitacionDto = habitacionService.getHabitacionDtoById(dto.getHabitacionDto().getId());
                entity.getHabitacion().setId(habitacionDto.getId());
                entity.getHabitacion().setNombre(habitacionDto.getNombre());
                entity.getHabitacion().setPrecio(habitacionDto.getPrecio());
                entity.getHabitacion().setStatus(habitacionDto.getStatus());
            }
		}
		return entity;
	}

	public List<TurnoDto> toDtoList(List<Turno> entities) throws Exception {
		List<TurnoDto> result = new ArrayList<>();
		for (Turno entityPerson : entities) {
			result.add(toDto(entityPerson));
		}
		return result;
	}
}
