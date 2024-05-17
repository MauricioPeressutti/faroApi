package com.project.faro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.faro.entity.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	@Query("SELECT p FROM person p WHERE p.docNumber LIKE concat('%',:docNumber,'%') ")
	public List<Person> findByDocNumber(@Param("docNumber") String docNumber);

	@Query("SELECT p FROM person p " + "WHERE upper(p.firstName) LIKE concat('%',:search,'%') "
			+ "OR upper(p.lastName) LIKE concat('%',:search,'%') ")
	public List<Person> findByNames(@Param("search") String search);

	/*@Query("SELECT p "
			+ " FROM person p "
			+ " INNER JOIN g.person p "
			+ " WHERE g.event.id = :eventId "
			+ " AND g.person.id = g.personHost.id")
	public List<Person> findHostListByEventId(@Param("eventId") Integer eventId);*/
}
