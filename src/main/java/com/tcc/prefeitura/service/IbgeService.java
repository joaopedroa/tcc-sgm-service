package com.tcc.prefeitura.service;

import java.util.List;

import com.tcc.prefeitura.model.Municipio;

public interface IbgeService {

	List<Municipio> buscarMunicipiosPorUF(String uf);
}
