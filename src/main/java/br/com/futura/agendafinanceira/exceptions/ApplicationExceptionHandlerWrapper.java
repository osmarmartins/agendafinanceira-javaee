package br.com.futura.agendafinanceira.exceptions;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class ApplicationExceptionHandlerWrapper extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	private final FacesContext facesContext = FacesContext.getCurrentInstance();
	private final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
	private final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
	
	private String mensagemErro;

	public ApplicationExceptionHandlerWrapper(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();

		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable exception = context.getException();

			boolean handled = false;

			try {

				requestMap.put("error-message", exception.getMessage());

				if (isException(exception, "ConstraintViolationException")) {
					handled = true;
					this.mensagemErro = "Registro não pode ser removido! Verifique suas associações.";
					redirect("/erro.xhtml");
				} else if (isException(exception, "org.hibernate.StaleObjectStateExcetion")) {
					handled = true;
					this.mensagemErro = "Registro alterado por outro usuário! Atualize os dados e repita a operação.";
					redirect("/erro.xhtml");
				}else if (exception instanceof ViewExpiredException) {
					handled = true;
					redirect("/login.xhtml");
				} else {
					handled = true;
					redirect("/erro.xhtml");
				}
				
//				navigationHandler.handleNavigation(context, fromAction, outcome, toFlowDocumentId);
				
			} finally {
				if (handled) {
					iterator.remove();
				}
			}
		}

		getWrapped().handle();
	}

	private void redirect(String page) {
		try {
			
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();

			facesContext.addMessage("mensagemErro", new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagemErro, ""));
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
