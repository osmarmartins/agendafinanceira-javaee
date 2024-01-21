package br.com.futura.agendafinanceira.exceptions;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ApplicationExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory factory;
	
	public ApplicationExceptionHandlerFactory(ExceptionHandlerFactory factory) {
		this.factory=factory;
	}
	
	@Override
	public ExceptionHandler getExceptionHandler() {
		return new ApplicationExceptionHandlerWrapper(factory.getExceptionHandler());
	}

}
