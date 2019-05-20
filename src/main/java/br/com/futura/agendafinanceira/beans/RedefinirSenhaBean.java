package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.futura.agendafinanceira.models.Usuario;
import br.com.futura.agendafinanceira.services.UsuarioService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class RedefinirSenhaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioLogado;
	
	@Inject
	private MessagesHelper messagesHelper;
	
	@Inject
	private UsuarioService usuarioService;
	
	@PostConstruct
	private void init() {
		Object login = SecurityContextHolder
							.getContext()
							.getAuthentication()
							.getPrincipal();
		
		this.usuarioLogado = usuarioService.loadUserByUsername((String) login);
	}
		
	public void salvar() {
		try {
			usuarioService.redefinirSenha(usuarioLogado);		
			messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		} catch (Exception e) {
			messagesHelper.addFlash(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); 
		}

	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

}
