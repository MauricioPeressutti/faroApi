package com.project.faro.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.faro.dto.HabitacionDto;
import com.project.faro.dto.UserDto;
import com.project.faro.security.entity.User;
import com.project.faro.security.service.PasswordService;
import com.project.faro.service.HabitacionService;
import com.project.faro.service.UserService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/secure/habitacion")
public class HabitacionController {

	@Resource
	HabitacionService habitacionService;

	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE }) 
	  public List<HabitacionDto> getAllHabitacionDto() throws Exception {
		  
		return habitacionService.getAllHabitacionDto();
		}
	  
	  //Obtengo las habitaciones listas para utilizar
	  @RequestMapping(value = "/active", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE }) 
	  public List<HabitacionDto> getActiveHabitacionDto() throws Exception {
		  
		return habitacionService.getActiveHabitacionDto();
		}

}