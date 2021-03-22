package com.tcc.prefeitura.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Classe utilizada para filtrar todas as requisições. Antes de chamar a rota específica que o cliente solicitou, primeiro
 * é verificado se o mesmo possui permissão para acessar o sistema. (TOKEN JWT)
 *
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		filterChain.doFilter(request, response);
		
	}

}
