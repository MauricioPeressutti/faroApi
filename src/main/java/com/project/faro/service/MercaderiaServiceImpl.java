package com.project.faro.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.faro.dto.HabitacionDto;
import com.project.faro.dto.MercaderiaDto;
import com.project.faro.dto.converter.MercaderiaConverter;
import com.project.faro.entity.Mercaderia;
import com.project.faro.repository.MercaderiaRepository;

@Service
public class MercaderiaServiceImpl implements MercaderiaService {
	
	@Resource
	MercaderiaConverter mercaderiaConverter;
	
	@Resource
	MercaderiaRepository mercaderiaRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public MercaderiaDto createMercaderia(MercaderiaDto newMercaderiaDto) {
		Mercaderia mercaderia = new Mercaderia();
		if(newMercaderiaDto.getCantidad() == null) {
			throw new RuntimeException("Debe cargar la cantidad de mercaderia.");
		}
		if(newMercaderiaDto.getNombre() == null) {
			throw new RuntimeException("Debe cargar el nombre de mercaderia.");
		}
		if(newMercaderiaDto.getPrecio() == null) {
			throw new RuntimeException("Debe cargar el precio de mercaderia.");
		}
		
		mercaderia = mercaderiaConverter.toEntity(newMercaderiaDto);
		mercaderia = mercaderiaRepository.save(mercaderia);
		return mercaderiaConverter.toDto(mercaderia);
	}
	
	@Override
	public List<MercaderiaDto> getMercaderiaByNombre(String nombre) throws Exception {
		return mercaderiaConverter.toDtoList(mercaderiaRepository.buscarPorAproximidad(nombre));
	}
	
	@Override
	public List<MercaderiaDto> getAllMercaderia() throws Exception {
		return mercaderiaConverter.toDtoList(mercaderiaRepository.findAll());
	}
	
	@Override
	@Transactional
	public MercaderiaDto addMercaderia(MercaderiaDto newMercaderiaDto) throws Exception {
		if(newMercaderiaDto.getId() == null) {
			throw new RuntimeException("Debe cargar la mercaderia a actualizar.");
		}
		Mercaderia mercaderia = mercaderiaRepository.findById(newMercaderiaDto.getId())
				.orElseThrow(() -> new Exception("Mercaderia no encontrada para el ID proporcionado: " + newMercaderiaDto.getId()));;
		if(newMercaderiaDto.getCantidad() == null) {
			throw new RuntimeException("Debe cargar la cantidad de mercaderia.");
		}
		if(newMercaderiaDto.getNombre() == null) {
			throw new RuntimeException("Debe cargar el nombre de mercaderia.");
		}
		if(newMercaderiaDto.getPrecio() == null) {
			throw new RuntimeException("Debe cargar el precio de mercaderia.");
		}
		
		mercaderia.setCantidad(mercaderia.getCantidad() + newMercaderiaDto.getCantidad());
		mercaderia.setPrecio(Double.parseDouble(newMercaderiaDto.getPrecio().toString().replace(",", ".")));
		mercaderia = mercaderiaRepository.save(mercaderia);
		return mercaderiaConverter.toDto(mercaderia);
	}

	@Override
	public List<Mercaderia> getMercaderiaListById(List<Integer> listaIdsMercaderia) {
		return mercaderiaRepository.findAllByMercaderiaIds(listaIdsMercaderia);
	}

	@Override
	public Mercaderia getMercaderiaById(Integer id) throws Exception {
		return mercaderiaRepository.findById(id).orElseThrow(() -> new Exception("Habitacion no encontrada para el ID proporcionado: " + id));
	}
	
	//Manejo el decrecimiento del stock
	@Override
		public void actualizarStock(List<MercaderiaDto> itemsMercaderia) {
	        for (MercaderiaDto item : itemsMercaderia) {
	            decrementarStock(item.getId(), 1);
	        }
	    }
		
		private void decrementarStock(Integer mercaderiaId, Integer cantidad) {
	        Mercaderia mercaderia = entityManager.find(Mercaderia.class, mercaderiaId);
	        if (mercaderia != null) {
	            int nuevaCantidad = mercaderia.getCantidad() - cantidad;
	            if (nuevaCantidad >= 0) {
	                mercaderia.setCantidad(nuevaCantidad);
	                entityManager.merge(mercaderia);
	            } else {
	                // Manejo de error: La cantidad a decrementar es mayor que la cantidad en stock
	                // Puedes lanzar una excepción, registrar un error, o manejarlo de otra manera apropiada
	                // Aquí lanzo una excepción como ejemplo
	                throw new IllegalArgumentException("Cantidad a decrementar mayor que la cantidad en stock para la mercadería con ID: " + mercaderiaId);
	            }
	        } else {
	            // Manejo de error: No se encontró la mercadería en la base de datos
	            // Puedes lanzar una excepción, registrar un error, o manejarlo de otra manera apropiada
	            // Aquí lanzo una excepción como ejemplo
	            throw new IllegalArgumentException("No se encontró la mercadería con ID: " + mercaderiaId);
	        }
	    }
}
