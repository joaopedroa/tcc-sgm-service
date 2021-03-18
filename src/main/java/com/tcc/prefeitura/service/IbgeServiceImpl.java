package com.tcc.prefeitura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.prefeitura.http.HttpClient;
import com.tcc.prefeitura.model.Municipio;

@Service
public class IbgeServiceImpl implements IbgeService {
	
	private final HttpClient httpClient;
	
	@Autowired
	public IbgeServiceImpl(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public List<Municipio> buscarMunicipiosPorUF(String uf) {
		return this.httpClient.getMunicipiosPorUF(uf);
	}
	
	

}
