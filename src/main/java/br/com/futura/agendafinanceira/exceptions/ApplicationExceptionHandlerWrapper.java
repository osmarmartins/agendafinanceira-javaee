package br.com.futura.agendafinanceira.exceptions;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

public class ApplicationExceptionHandlerWrapper extends ExceptionHandlerWrapper {
	
    private ExceptionHandler handler;
    
    public ApplicationExceptionHandlerWrapper(ExceptionHandler handler) {
        this.handler = handler;
    }
 
    @Override
    public ExceptionHandler getWrapped() {
        return handler;
    }
 
    @Override
    public void handle() throws FacesException {
        final Iterator<ExceptionQueuedEvent> event = getUnhandledExceptionQueuedEvents().iterator();
 
        while (event.hasNext()) {
            Throwable throwable = event.next().getContext().getException();
 
            final FacesContext context = FacesContext.getCurrentInstance();
            final NavigationHandler navigation = context.getApplication().getNavigationHandler();
            final Map<String, Object> request = context.getExternalContext().getRequestMap();
 
            try {
                request.put("error-message", throwable.getMessage());
                navigation.handleNavigation(context, null, "/erro");
                context.renderResponse();
            } finally {
                event.remove();
            }
        }
 
        getWrapped().handle();
    }	

}
