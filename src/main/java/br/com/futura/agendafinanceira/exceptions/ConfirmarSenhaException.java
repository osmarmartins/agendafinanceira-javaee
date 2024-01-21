package br.com.futura.agendafinanceira.exceptions;

public class ConfirmarSenhaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConfirmarSenhaException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfirmarSenhaException(String message) {
		super(message);
	}
	
	

}
