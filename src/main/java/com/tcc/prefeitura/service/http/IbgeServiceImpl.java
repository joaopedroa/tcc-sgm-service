package com.tcc.prefeitura.service.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tcc.prefeitura.expections.HttpClientErroException;
import com.tcc.prefeitura.model.Estado;
import com.tcc.prefeitura.model.Municipio;
import com.tcc.prefeitura.service.DependencyFactory;

import lombok.extern.slf4j.Slf4j;

@Service
public class IbgeServiceImpl implements IbgeService {

	Logger logger = LoggerFactory.getLogger(IbgeServiceImpl.class);

	private final DependencyFactory dependencyFactory;

	private final HttpClient httpClient;

	@Autowired
	public IbgeServiceImpl(DependencyFactory dependencyFactory, HttpClient httpClient) {
		this.dependencyFactory = dependencyFactory;
		this.httpClient = httpClient;
	}

	@Override
	public List<Municipio> getMunicipiosPorUF(String uf) {
		try {
			String uri = dependencyFactory.getUrlIBGE() + "/v1/localidades/estados/" + uf + "/municipios";

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(uri))
					.GET()
					.build();
			
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
			
			if(response.statusCode() == 200) {
				return new Gson().fromJson(response.body(), new TypeToken<List<Municipio>>(){}.getType());
			}
			
			throw new HttpClientErroException("Erro ao buscar informações de distritos");
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Arrays.asList();
		}
	}

	@Override
	public List<Estado> getTodosEstados() {
		try {
			String uri = dependencyFactory.getUrlIBGE() + "/v1/localidades/estados";

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(uri))
					.GET()
					.build();
			
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
			
			if(response.statusCode() == 200) {
				return new Gson().fromJson(response.body(), new TypeToken<List<Estado>>(){}.getType());
			}
			
			throw new HttpClientErroException("Erro ao buscar estados");
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Arrays.asList();
		}
	}

	@Override
	public String getMalhaPorMunicipio(Long idMunicipio) {
		try {
			String uri = dependencyFactory.getUrlIBGE() + "/v3/malhas/municipios/" + idMunicipio;

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(uri))
					.GET()
					.build();
			
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
			
			if(response.statusCode() == 200) {
				return response.body();
			}
			
			throw new HttpClientErroException("Erro ao buscar malha de município com id: " + idMunicipio);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

}
