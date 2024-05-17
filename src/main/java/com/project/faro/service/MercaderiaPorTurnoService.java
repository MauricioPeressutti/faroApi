package com.project.faro.service;

import java.util.List;
import java.util.Map;

import com.project.faro.dto.MercaderiaDto;
import com.project.faro.dto.TurnoDto;
import com.project.faro.entity.MercaderiaPorTurno;

public interface MercaderiaPorTurnoService {

	List<MercaderiaPorTurno> getMercaderiaByTurnoId(Integer id);

	Map<String, String> addMercaderia(TurnoDto turnoDto) throws Exception;

}
