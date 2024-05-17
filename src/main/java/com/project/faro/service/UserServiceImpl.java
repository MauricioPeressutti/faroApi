package com.project.faro.service;

import org.springframework.stereotype.Service;

import com.project.faro.dto.PersonDto;
import com.project.faro.dto.UserDto;
import com.project.faro.entity.Person;
import com.project.faro.repository.UserRepository;
import com.project.faro.security.entity.User;
import com.project.faro.security.service.PasswordService;
import com.project.faro.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserRepository userRepository;

	@Resource
	PersonService personService;

	@Resource
	PasswordService passwordService;

	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDto create(UserDto newUser) throws Exception {
		if (newUser == null && newUser.getDocNumber() == null) {
			throw new Exception("Faltan datos para crear el usuario.");
		}
		User user = userRepository.findByUsername(newUser.getUsername());
		if (user != null)
			throw new Exception("El usuario ya existe.");

		List<PersonDto> personDto = personService.personListbyDocNumber(newUser.getUsername());
		if (personDto != null && !personDto.isEmpty()) {
			user = new User();
			user.setUsername(newUser.getUsername());
			user.setDefaultPass(true);
			user.setPerson(new Person(personDto.get(0).getId())); // Puede venir mas de una persona??
			user.setPassword(passwordService.generateDefaultPassword(newUser.getUsername())); // La contraseña por
																								// default es 1234
		} else {
			if (newUser.getFirstName() == null || newUser.getLastName() == null || newUser.getEmail() == null)
				throw new Exception("Faltan datos para crear el usuario.");

			Person person = new Person();
			person.setDocNumber(newUser.getUsername());
			person.setFirstName(newUser.getFirstName());
			person.setLastName(newUser.getLastName());
			person.setPhone(newUser.getPhone());
			person = personService.create(person);

			user = new User();
			user.setUsername(newUser.getUsername());
			user.setDefaultPass(true);
			user.setPerson(person);
			user.setIsOwner(newUser.getIsOwner());
			user.setPassword(passwordService.generateDefaultPassword(newUser.getUsername())); // La contraseña por
																								// default es 1234
		}
		user = userRepository.save(user);

		return null;
	}
}
