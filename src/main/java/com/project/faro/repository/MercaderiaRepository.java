package com.project.faro.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.faro.entity.Mercaderia;
@Repository
public interface MercaderiaRepository extends JpaRepository<Mercaderia, Integer> {

	@Query("SELECT m FROM mercaderia m WHERE m.nombre LIKE %:nombre% ")
			//+ "AND m.cantidad > 0 ")
    List<Mercaderia> buscarPorAproximidad(@Param("nombre") String nombre);

	@Query("SELECT m FROM mercaderia m WHERE m.id in :listaIdsMercaderia ")
	List<Mercaderia> findAllByMercaderiaIds(@Param("listaIdsMercaderia") List<Integer> listaIdsMercaderia);

}
