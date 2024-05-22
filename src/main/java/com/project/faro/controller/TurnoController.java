package com.project.faro.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.faro.dto.BalanceDto;
import com.project.faro.dto.TurnoDto;
import com.project.faro.security.entity.User;
import com.project.faro.security.token.JwtTokenFactory;
import com.project.faro.service.TurnoService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/secure/turno")
public class TurnoController {
	private static final Logger logger = LoggerFactory.getLogger(TurnoController.class);
	
	
	@Resource
	TurnoService turnoService;
	@Resource
	JwtTokenFactory jwtTokenFactory;

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TurnoDto create(@RequestBody TurnoDto newTurno, HttpServletRequest request) throws Exception {
		String token = request.getHeader("Authorization");
		User user = jwtTokenFactory.getUserByToken(token);
		logger.info("Extracted user ID: {}", user.getId());
		return turnoService.createTurno(newTurno, user.getId());
	}
	
	//Se usar para Cerrar el turno y para agregar tiempo al turno
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TurnoDto update(@RequestBody TurnoDto newTurno, HttpServletRequest request) throws Exception {
		String token = request.getHeader("Authorization");
		User user = jwtTokenFactory.getUserByToken(token);
		return turnoService.updateTurno(newTurno, user.getId());
	}

	//Obtengo los turnos activos (Que estan ocurriendo en este momenbto)
	  @RequestMapping(value = "/active", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE }) 
	  public List<TurnoDto> getActiveTurnoDto() throws Exception {
		return turnoService.getActiveTurnoDto();
		}
	  
	//Obtengo los turnos finalizados (para el balance)
	  @RequestMapping(value = "/balance", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE }) 
	  public BalanceDto getBalanceTurnoDto() throws Exception {
		return turnoService.getBalanceTurnoDto();
		}
	  
	  
		//Obtengo los turnos finalizados (para el balance)
		  @RequestMapping(value = "/balance/{days}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE }) 
		  public BalanceDto getBalanceForDayTurnoDto(@PathVariable int days) throws Exception {
			return turnoService.getBalanceForDayTurnoDto(days);
			}
}