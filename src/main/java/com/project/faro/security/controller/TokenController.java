package com.project.faro.security.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.project.faro.dto.PersonDto;
import com.project.faro.dto.converter.PersonConverter;
import com.project.faro.entity.Person;
import com.project.faro.exception.ConflictRequestException;
import com.project.faro.security.dto.UserLogin;
import com.project.faro.security.entity.User;
import com.project.faro.security.service.PasswordService;
import com.project.faro.security.token.JwtTokenFactory;
import com.project.faro.security.token.TokenPair;
import com.project.faro.security.token.TokenType;
import com.project.faro.service.PersonService;
import com.project.faro.service.UserService;

import javax.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/token")
public class TokenController {
	Boolean authenticated = false;

	@Resource
	UserService userService;

	@Resource
	PasswordService passwordService;

	@Resource
	PersonService personService;

	@Resource
	PersonConverter personConverter;

	@Resource
	JwtTokenFactory jwtTokenFactory;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TokenPair login(@RequestBody UserLogin userLogin) throws Exception {
		this.authenticated = false;

		String username = userLogin.getUsername();
		String password = userLogin.getPassword();

		if (username == null || password == null)
			throw new Exception("Por favor, complete todos los campos");

		Person person = null;

		// buscar usuario en la base
		User user = userService.getByUsername(username);
		if (user == null) {
			throw new Exception("El usuario no existe, verifique sus datos");
		}

		// Valida login con usuario y password
		if (passwordService.checkPasswod(password, username, user.getPassword())) {
			this.authenticated = true;
			person = personService.getPersonByIdEntity(user.getPerson().getId());
		}else {
			throw new  ConflictRequestException("Nombre de usuario y/o clave incorrecto");
		}

		if (person == null)
			throw new Exception("Mensaje ERROR");

		if (!authenticated) {
			throw new ConflictRequestException("Nombre de usuario y/o clave incorrecto");
		}

		// UserDto userDto = null; //TODO converter
		TokenPair tokenPair = new TokenPair();

		tokenPair.setAuthToken(jwtTokenFactory.createTokenOne(user, TokenType.AUTH));
		tokenPair.setRefreshToken(jwtTokenFactory.createTokenOne(user, TokenType.REFRESH));

		PersonDto personDto = personConverter.toDto(person);
		personDto.setUserId(user.getId());
		personDto.setUserOwner(user.getIsOwner());
		tokenPair.setPersonDto(personDto);

		return tokenPair;
	}
}
