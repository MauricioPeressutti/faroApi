package com.project.faro.service;

import java.util.List;

import com.project.faro.dto.MercaderiaDto;
import com.project.faro.entity.Habitacion;
import com.project.faro.entity.Mercaderia;

public interface MercaderiaService {

	MercaderiaDto createMercaderia(MercaderiaDto newMercaderia);

	List<MercaderiaDto> getMercaderiaByNombre(String nombre) throws Exception;

	MercaderiaDto addMercaderia(MercaderiaDto newMercaderiaDto) throws Exception;

	List<MercaderiaDto> getAllMercaderia() throws Exception;

	List<Mercaderia> getMercaderiaListById(List<Integer> listaIdsMercaderia);

	Mercaderia getMercaderiaById(Integer id) throws Exception;

	void actualizarStock(List<MercaderiaDto> itemsMercaderia);

}
