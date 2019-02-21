package br.com.futura.agendafinanceira.exceptions;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.persistence.PersistenceException;
import javax.transaction.RollbackException;

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
            Map<String, Object> request = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();

            Throwable exception = context.getException();

            boolean handled = false;

            try {
            	if (exception instanceof ViewExpiredException) {
                    handled = true;
                    redirect("/login.xhtml");
                } else if (exception instanceof RollbackException) {
                	request.put("error-message", "Não foi possível realizar a operção no banco de dados.");                	
                    handled = true;
                    redirect("/erro.xhtml");
                } else {
                	request.put("error-message", exception.getMessage());                	
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
	
	
	
	
	
//    private ExceptionHandler handler;
//    
//    public ApplicationExceptionHandlerWrapper(ExceptionHandler handler) {
//        this.handler = handler;
//    }
// 
//    @Override
//    public ExceptionHandler getWrapped() {
//        return handler;
//    }
// 
//    @Override
//    public void handle() throws FacesException {
//        final Iterator<ExceptionQueuedEvent> event = getUnhandledExceptionQueuedEvents().iterator();
// 
//        while (event.hasNext()) {
//            Throwable throwable = event.next().getContext().getException();
// 
//            final FacesContext context = FacesContext.getCurrentInstance();
//            final NavigationHandler navigation = context.getApplication().getNavigationHandler();
//            final Map<String, Object> request = context.getExternalContext().getRequestMap();
// 
//            try {
//            	if (throwable.equals(javax.persistence.PersistenceException.class) ) {
//            		request.put("error-message", "Não foi possível realizar a operção no banco de dados.");
//            	}else {
//            		request.put("error-message", throwable.getMessage());
//            	}
//            		
//                navigation.handleNavigation(context, null, "/erro");
//                context.renderResponse();
//            } finally {
//                event.remove();
//            }
//        }
// 
//        getWrapped().handle();
//    }	

}
