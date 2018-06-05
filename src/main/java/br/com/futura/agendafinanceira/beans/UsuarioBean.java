package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Usuario;
import br.com.futura.agendafinanceira.services.UsuarioService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios = new ArrayList<>();

	private String filtro;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.usuarios = usuarioService.listarTodos();
	}

	public void excluir(Usuario usuario) {
		usuarioService.excluir(usuario);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}

	public void pesquisar() {
		if (this.filtro != null && !this.filtro.isEmpty()) {
			usuarios = usuarioService.listarPor(this.filtro);
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
}
