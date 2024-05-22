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
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    @Column(name = "username")
    @JsonProperty("username")
    private String username;

    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    @Column(name = "is_owner", columnDefinition = "bit default false") // Ajusta la definición de columna
    @JsonProperty("isOwner")
    private Boolean isOwner;

    // bi-directional many-to-one association to Person
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonProperty("person")
    private Person person;

    @Column(name = "default_pass", columnDefinition = "bit default false") // Ajusta la definición de columna
    @JsonProperty("defaultPass")
    private boolean defaultPass;
}
