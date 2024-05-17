package com.project.faro.dto.converter;

import org.springframework.stereotype.Service;

import com.project.faro.dto.PersonDto;
import com.project.faro.entity.Person;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonConverter {

	public PersonDto toDto(Person person) {
		PersonDto dto = new PersonDto();
		if (person != null) {
			dto.setId(person.getId());
			dto.setFirstName(person.getFirstName());
			dto.setLastName(person.getLastName());
			dto.setDocNumber(person.getDocNumber());
			dto.setPhone(person.getPhone());
		}
		return dto;
	}

	public Person toEntity(PersonDto dto) {
		Person entity = new Person();
		if (dto != null) {
			entity.setId(dto.getId());
			entity.setFirstName(dto.getFirstName());
			entity.setLastName(dto.getLastName());
			entity.setDocNumber(dto.getDocNumber());
			entity.setPhone(dto.getPhone());
		}
		return entity;
	}

	public List<PersonDto> toDtoList(List<Person> entities) throws Exception {
		List<PersonDto> result = new ArrayList<>();
		for (Person entityPerson : entities) {
			result.add(toDto(entityPerson));
		}
		return result;
	}
}
