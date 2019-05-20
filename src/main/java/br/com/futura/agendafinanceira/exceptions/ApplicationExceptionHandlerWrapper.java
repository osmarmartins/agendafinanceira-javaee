package br.com.futura.agendafinanceira.exceptions;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import br.com.futura.agendafinanceira.utils.FacesUtil;

public class ApplicationExceptionHandlerWrapper extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public ApplicationExceptionHandlerWrapper(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {

		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();
		while (events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			Throwable exception = context.getException();
			NegocioException negocioException = getNegocioException(exception);
			NenhumResultadoException nenhumResultadoException = getNenhumResultadoException(exception);
			
			boolean handled = false;

			try {

				if (exception instanceof ViewExpiredException) {
					handled = true;
					redirect("/login.xhtml");
				} else if (negocioException != null) {
					handled = true;
					FacesUtil.addSeverityError(negocioException.getMessage());					
				} else if (nenhumResultadoException != null) {
					handled = true;
					FacesUtil.addSeverityError(nenhumResultadoException.getMessage());					
				}else if (isException(exception, "ConstraintViolationException")) {
					handled = true;
					FacesUtil.addSeverityError("Registro não pode ser removido! Verifique suas associações.");
				} else if (isException(exception, "org.hibernate.StaleObjectStateExcetion")) {
					handled = true;
					FacesUtil.addSeverityError("Registro alterado por outro usuário! Atualize os dados e repita a operação.");
				} else if (isException(exception, "br.com.futura.agendafinanceira.exceptions.ConfirmarSenhaException")) {
					handled = true;
					FacesUtil.addSeverityError("Senhas não conferem! Repita a operação.");
				} else {
					handled = true;
					redirect("/erro.xhtml");
				}
				
			} finally {
				if (handled) {
					events.remove();
				}
			}
		}

		getWrapped().handle();
	}
	
	private NegocioException getNegocioException(Throwable exception) {
		if (exception instanceof NegocioException) {
			return (NegocioException) exception;
		} else if (exception.getCause() != null) {
			return getNegocioException(exception.getCause());
		}
		
		return null;
	}	

	private NenhumResultadoException getNenhumResultadoException(Throwable exception) {
		if (exception instanceof NenhumResultadoException) {
			return (NenhumResultadoException) exception;
		} else if (exception.getCause() != null) {
			return getNenhumResultadoException(exception.getCause());
		}
		
		return null;
	}	
	
	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();

			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

	private boolean isException(Throwable exception, String message) {
		return exception != null && exception.getMessage() != null && exception.getMessage().indexOf(message) != -1;
	}

}
