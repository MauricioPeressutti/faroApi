package com.project.faro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.faro.entity.Habitacion;
import com.project.faro.entity.Turno;
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {

	@Query("SELECT t FROM turno t " +
            "WHERE t.status = :status " )
    public List<Turno> findTurnoByStatus(@Param("status") String status);
	
	@Query("SELECT t FROM turno t WHERE t.status = :status AND t.dateInit >= :startDate")
    List<Turno> findTurnoByStatusAndDateInitAfter(@Param("status") String status, @Param("startDate") Date startDate);
	
}
