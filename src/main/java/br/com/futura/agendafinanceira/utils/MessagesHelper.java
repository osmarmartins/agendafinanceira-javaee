package br.com.futura.agendafinanceira.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MessagesHelper {
	
	@Inject
	private FacesContext facesContext;
	
	public void addFlash(FacesMessage facesMessage){
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, facesMessage);
	}

}
