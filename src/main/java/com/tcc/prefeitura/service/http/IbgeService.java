package com.tcc.prefeitura.service.http;

import java.util.List;

import com.tcc.prefeitura.model.Estado;
import com.tcc.prefeitura.model.Municipio;

public interface IbgeService {
	
	List<Municipio> getMunicipiosPorUF(String uf);
	
	List<Estado> getTodosEstados();
	
	String getMalhaPorMunicipio(Long idMunicipio);

}
