package com.project.faro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.faro.dto.MercaderiaDto;
import com.project.faro.dto.TurnoDto;
import com.project.faro.service.MercaderiaPorTurnoService;
import com.project.faro.service.MercaderiaService;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/secure/mercaderia")
public class MercaderiaController {

	@Autowired
	MercaderiaService mercaderiaService;
	@Autowired
	MercaderiaPorTurnoService mercaderiaPorTurnoService;

	@RequestMapping(value = "/names", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE }) 
	public List<MercaderiaDto> getMercaderiaByNombre(@RequestParam("search") String search) throws Exception {
	    return mercaderiaService.getMercaderiaByNombre(search);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE }) 
	public List<MercaderiaDto> getAllMercaderia() throws Exception {
	    return mercaderiaService.getAllMercaderia();
	}
	  
	  @RequestMapping(value = "/create", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
		public MercaderiaDto create(@RequestBody MercaderiaDto newMercaderia, HttpServletRequest request) throws Exception {
			return mercaderiaService.createMercaderia(newMercaderia);
		}
	  
	  @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
		public MercaderiaDto update(@RequestBody MercaderiaDto newMercaderia, HttpServletRequest request) throws Exception {
			return mercaderiaService.addMercaderia(newMercaderia);
		}
	  
	  @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
		public Map<String, String> addMercaderia(@RequestBody TurnoDto turnoDto, HttpServletRequest request) throws Exception {
			return mercaderiaPorTurnoService.addMercaderia(turnoDto);
		}
}