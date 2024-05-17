package com.project.faro.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.faro.dto.UserDto;
import com.project.faro.security.entity.User;
import com.project.faro.security.service.PasswordService;
import com.project.faro.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/secure/user")
public class UserController {

	@Resource
	UserService userService;

	@Resource
	PasswordService passwordService;

	/*
	 * @RequestMapping(value = "/prueba", method = RequestMethod.GET, produces = {
	 * MediaType.APPLICATION_JSON_VALUE }) public User prueba() { Boolean b =
	 * passwordService.checkPasswod("1234", "11222333", "1234"); return
	 * userService.getByUsername("11222333"); }
	 */

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserDto create(@RequestBody UserDto newUser, HttpServletRequest request) throws Exception {
		return userService.create(newUser);
	}

}