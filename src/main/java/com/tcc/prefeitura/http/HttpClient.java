package com.tcc.prefeitura.http;

import java.util.List;

import com.tcc.prefeitura.model.Municipio;

public interface HttpClient {
	
	List<Municipio> getMunicipiosPorUF(String uf);

}
