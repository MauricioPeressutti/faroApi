package com.project.faro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.faro.entity.Mercaderia;
import com.project.faro.entity.MercaderiaPorTurno;

@Repository
public interface MercaderiaPorTurnoRepository extends JpaRepository<MercaderiaPorTurno, Integer> {

	@Query("SELECT m FROM mercaderiaPorTurno m WHERE m.turnoId = :id")
	List<MercaderiaPorTurno> findByTurnoId(@Param("id") Integer id);

}
