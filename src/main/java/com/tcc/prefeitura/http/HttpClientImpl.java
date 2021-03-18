package com.tcc.prefeitura.http;

import java.net.URI;
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
import com.tcc.prefeitura.model.Municipio;
import com.tcc.prefeitura.service.DependencyFactory;

@Service
public class HttpClientImpl implements HttpClient {

	Logger logger = LoggerFactory.getLogger(HttpClientImpl.class);

	private final DependencyFactory dependencyFactory;

	private final java.net.http.HttpClient httpClient;

	@Autowired
	public HttpClientImpl(DependencyFactory dependencyFactory, java.net.http.HttpClient httpClient) {
		this.dependencyFactory = dependencyFactory;
		this.httpClient = httpClient;
	}

	@Override
	public List<Municipio> getMunicipiosPorUF(String uf) {
		try {
			String uri = dependencyFactory.getUrlIBGE() + "/v1/localidades/estados/" + uf + "/municipios";

			// @formatter:off
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(uri))
					.GET()
					.build();
			
			
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
			
			if(response.statusCode() == 200) {
				return new Gson().fromJson(response.body(), new TypeToken<List<Municipio>>(){}.getType());
			}
			// @formatter:on

			throw new HttpClientErroException("Erro ao buscar informações de distritos");
		} catch (Exception e) {
			logger.error("Erro ao buscar Distritos por UF", e);
			return Arrays.asList();
		}
	}

}
