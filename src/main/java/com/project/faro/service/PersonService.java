package com.project.faro.service;

import java.util.List;

import com.project.faro.dto.PersonDto;
import com.project.faro.entity.Person;

public interface PersonService {
	PersonDto getPersonByIdDto(Integer id);

	Person getPersonByIdEntity(Integer id);

	PersonDto setPersonDto(PersonDto personDto) throws Exception;

	PersonDto updatePerson(PersonDto personDto) throws Exception;

	List<PersonDto> personListbyDocNumber(String docNumber) throws Exception;

	List<PersonDto> personListbyNames(String search) throws Exception;

	Person create(Person person) throws Exception;

	/*List<PersonDto> getHostListByEventId(Integer id) throws Exception;*/
}
