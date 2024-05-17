package com.project.faro.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.faro.entity.Person;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private Integer id;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@Column(columnDefinition = "not null default false")
	@JsonProperty("isOwner")
	private Boolean isOwner;

	// bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "person_id")
	@JsonProperty("person")
	private Person person;
	@JsonProperty("defaultPass")
	private boolean defaultPass;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
	public boolean isDefaultPass() {
		return defaultPass;
	}

	public void setDefaultPass(boolean defaultPass) {
		this.defaultPass = defaultPass;
	}
}
