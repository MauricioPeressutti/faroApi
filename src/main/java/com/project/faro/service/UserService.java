package com.project.faro.service;

import com.project.faro.dto.UserDto;
import com.project.faro.security.entity.User;

public interface UserService {
	User getByUsername(String userName);

	UserDto create(UserDto newUser) throws Exception;
}
