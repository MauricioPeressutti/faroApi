package com.project.faro.service;

import org.springframework.stereotype.Service;

import com.project.faro.dto.PersonDto;
import com.project.faro.dto.converter.PersonConverter;
import com.project.faro.entity.Person;
import com.project.faro.repository.PersonRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

	@Resource
	PersonRepository personRepository;

	@Resource
	PersonConverter personConverter;

	@Override
	public PersonDto getPersonByIdDto(Integer id) {
		PersonDto res = new PersonDto();
		Person personEntity = getPersonByIdEntity(id);
		if (personEntity != null) {
			res = personConverter.toDto(personEntity);
		}
		return res;
	}

	@Override
	public Person getPersonByIdEntity(Integer id) {
		return personRepository.findById(id).orElse(null);
	}

	@Override
	public PersonDto setPersonDto(PersonDto personDto) throws Exception {
		Person person;
		if (personDto == null) {
			throw new Exception("Se debe enviar datos de persona para actualizar");
		}
		if (personDto.getDocNumber() == null) {
			throw new Exception("Se debe enviar DNI de persona para actualizar");
		}
		if (personDto.getLastName() == null) {
			throw new Exception("Se debe enviar Apellido de persona para actualizar");
		}

		person = personConverter.toEntity(personDto);
		if (person != null) {
			person = personRepository.save(person);
		}

		return personConverter.toDto(person);
	}

	@Override
	public PersonDto updatePerson(PersonDto personDto) throws Exception {
		if (personDto == null) {
			throw new Exception("Se debe enviar datos de persona para actualizar");
		}
		if (personDto.getId() == null) {
			throw new Exception("Se debe enviar id de persona para actualizar");
		}
		if (personDto.getDocNumber() == null) {
			throw new Exception("Se debe enviar DNI de persona para actualizar");
		}
		if (personDto.getLastName() == null) {
			throw new Exception("Se debe enviar Apellido de persona para actualizar");
		}

		Person person = getPersonByIdEntity(personDto.getId());
		if (person == null) {
			throw new Exception("La persona con ID no existe");
		}
		person = personConverter.toEntity(personDto);
		person = personRepository.save(person);

		return personConverter.toDto(person);
	}

	@Override
	public List<PersonDto> personListbyDocNumber(String docNumber) throws Exception {
		if (docNumber == null) {
			throw new Exception("se debe enviar numero de documento");
		}
		List<Person> persons = personRepository.findByDocNumber(docNumber);
//		if (persons == null || persons.isEmpty()) {
//			throw new Exception("No ha encontrado personas con dicho datos");
//		}
		return personConverter.toDtoList(persons);
	}

	@Override
	public List<PersonDto> personListbyNames(String search) throws Exception {
		if (search == null) {
			throw new Exception("se debe enviar nombre o apellido de la persona");
		}
		List<Person> persons = personRepository.findByNames(search.toUpperCase());
		if (persons == null || persons.isEmpty()) {
			throw new Exception("No ha encontrado personas con dicho datos");
		}
		return personConverter.toDtoList(persons);
	}

	@Override
	public Person create(Person person) throws Exception {
		List<PersonDto> p = personListbyDocNumber(person.getDocNumber());
		if (p != null && !p.isEmpty())
			//throw new Exception("Ya existe una person con du " + person.getDocNumber());
			//Elimine el control de que la persona ya existe // tal vez tiene que ir
			return personConverter.toEntity(p.get(0));
		return personRepository.save(person);
	}

	/*@Override
	public List<PersonDto> getHostListByEventId(Integer eventId) throws Exception {
		List<Person> list = personRepository.findHostListByEventId(eventId);
		return personConverter.toDtoList(list);
	}*/
}
