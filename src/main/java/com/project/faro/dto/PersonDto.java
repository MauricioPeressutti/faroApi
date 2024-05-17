package com.project.faro.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
	private Integer id;
	private String firstName;
	private String lastName;
	private String docNumber;
	private String email;
	private String phone;
	private Integer userId;
	private boolean userOwner;
}
