package com.project.faro.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.project.faro.dto.MercaderiaDto;
import com.project.faro.dto.TurnoDto;
import com.project.faro.entity.MercaderiaPorTurno;
import com.project.faro.exception.ConflictRequestException;
import com.project.faro.repository.MercaderiaPorTurnoRepository;

@Service
public class MercaderiaPorTurnoServiceImpl implements MercaderiaPorTurnoService {
	
	@Autowired
	private MercaderiaPorTurnoRepository mercaderiaPorTurnoRepository;
	
	@Lazy
	@Autowired
	private TurnoService turnoService;
	@Autowired
	private MercaderiaService mercaderiaService;

	@Override
	public List<MercaderiaPorTurno> getMercaderiaByTurnoId(Integer id) {
		
		return mercaderiaPorTurnoRepository.findByTurnoId(id);
	}

	@Override
	@Transactional
	public Map<String, String> addMercaderia(TurnoDto turnoDto) throws Exception {
		if(turnoDto.getMercaderiaList().isEmpty()) {
			throw new ConflictRequestException("Debe cargar al menos una consumicion.");
		}
		List<MercaderiaPorTurno> listaMercaderiaPorTurno = new ArrayList<>();
		Map<String, String> respuesta = new HashMap<>();
		Double montoTotal = 0D;
		//Actualizo la mercaderia
		mercaderiaService.actualizarStock(turnoDto.getMercaderiaList());
		for(MercaderiaDto mercaderia : turnoDto.getMercaderiaList()) {
			MercaderiaPorTurno entity = new MercaderiaPorTurno();
			entity.setTurnoId(turnoDto.getId());
			entity.setMercaderia(mercaderiaService.getMercaderiaById(mercaderia.getId()));
			entity.setPrecio(mercaderia.getPrecio());
			
			montoTotal += mercaderia.getPrecio();
			listaMercaderiaPorTurno.add(entity);
		}
		try {
			mercaderiaPorTurnoRepository.saveAll(listaMercaderiaPorTurno);
			respuesta.put("estado", "ok");
			//ACT EL MONTO TOTAL
			turnoDto.setPrecioTotal(turnoDto.getPrecioTotal() + montoTotal);
			turnoService.updateTurno(turnoDto, null);
		} catch (Exception e) {
			throw new Exception("Problema al guardar las consumiciones");
		}
		
		return respuesta;
	}

}
