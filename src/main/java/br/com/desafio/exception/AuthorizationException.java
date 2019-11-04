package br.com.desafio.exception;

public class AuthorizationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String msg) {
	 
		super(msg);
	 
	}
	
	public AuthorizationException(String msg,Throwable cause) {
		super(msg,cause);
	}
	
}
