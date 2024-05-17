package com.project.faro.security.token;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.faro.dto.PersonDto;
import com.project.faro.dto.UserDto;
import com.project.faro.security.token.JwtToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TokenPair {

	private JwtToken authToken;
	private JwtToken refreshToken;
	private PersonDto person;
	private UserDto user;

	public JwtToken getAuthToken() {
		return authToken;
	}

	public void setAuthToken(JwtToken authToken) {
		this.authToken = authToken;
	}

	public JwtToken getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(JwtToken refreshToken) {
		this.refreshToken = refreshToken;
	}

	public PersonDto getPerson() {
		return person;
	}

	public void setPersonDto(PersonDto person) {
		this.person = person;
	}

}
