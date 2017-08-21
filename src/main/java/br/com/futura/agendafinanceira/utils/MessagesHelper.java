package br.com.futura.agendafinanceira.utils;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MessagesHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesContext facesContext;
	
	public void addFlash(FacesMessage facesMessage){
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, facesMessage);
	}

}
