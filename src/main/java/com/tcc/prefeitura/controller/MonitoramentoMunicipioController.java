package com.tcc.prefeitura.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.prefeitura.model.Municipio;
import com.tcc.prefeitura.service.IbgeService;

@RestController
@RequestMapping("/monitoramento")
public class MonitoramentoMunicipioController {
	
	private final IbgeService ibgeService;
	
	public MonitoramentoMunicipioController(IbgeService ibgeService) {
		this.ibgeService = ibgeService;
	}
	
	@GetMapping("/ibge/distritos/{UF}")
	public ResponseEntity<List<Municipio>> getMunicipios(@PathVariable String uf){
		List<Municipio> municipios = this.ibgeService.buscarMunicipiosPorUF(uf);
		return new ResponseEntity<List<Municipio>>(municipios, HttpStatus.OK);
	}
	

}
