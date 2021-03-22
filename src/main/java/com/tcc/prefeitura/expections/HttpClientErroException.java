package com.tcc.prefeitura.expections;

public class HttpClientErroException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public HttpClientErroException(String msg) {
		super(msg);
	}
	
	public HttpClientErroException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
