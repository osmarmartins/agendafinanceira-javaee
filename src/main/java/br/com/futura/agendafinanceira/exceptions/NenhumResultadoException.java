package br.com.futura.agendafinanceira.exceptions;

public class NenhumResultadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NenhumResultadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NenhumResultadoException(String message) {
		super(message);
	}	

}
