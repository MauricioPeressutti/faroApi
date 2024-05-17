package com.project.faro.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogin {

	private transient String username;
	private transient String password;
	private boolean isOwner;
}
