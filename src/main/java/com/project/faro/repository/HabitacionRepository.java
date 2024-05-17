package com.project.faro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.faro.entity.Habitacion;
@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

	@Query("SELECT h FROM habitacion h " +
            "WHERE h.status = :status " )
    public List<Habitacion> findHabitacionByStatus(@Param("status") String status);

	
}
