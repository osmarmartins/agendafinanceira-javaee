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

	private String pesquisaFiltro;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private MessagesHelper messagesHelper;
	
	private List<Usuario> usuariosSelecionados;
	
	private String mensagemExclusao;

	@PostConstruct
	private void init() {
		this.usuarios = usuarioService.listarTodos();
		this.usuariosSelecionados = new ArrayList<>();
		this.mensagemExclusao = new String();
	}
	
	public Boolean isExisteSelecao() {
		return !usuariosSelecionados.isEmpty();
	}
	
	public void selecionaUsuario(Usuario usuario) {
		usuariosSelecionados.add(usuario);
		mensagemExclusaoBuilder();
	}
	
	public void mensagemExclusaoBuilder() {
		StringBuilder msg = new StringBuilder();

		if (this.getUsuariosSelecionados()!=null && !this.getUsuariosSelecionados().isEmpty()) {
			msg.append("Excluir permanentemente ");
			if (this.usuariosSelecionados.size()>1) {
				msg.append("os usuários selecionados?");
			}else {
				msg.append("o usuário ");
				msg.append(usuariosSelecionados.get(0).getNome());
			}
		}
		this.mensagemExclusao = msg.toString();
	}
		
	
	public void excluir() {
		usuarioService.excluir(usuariosSelecionados);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}

	public void pesquisar() {
		if (this.pesquisaFiltro != null && !this.pesquisaFiltro.isEmpty()) {
			usuarios = usuarioService.listarPor(this.pesquisaFiltro);
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setPesquisaFiltro(String pesquisaFiltro) {
		this.pesquisaFiltro = pesquisaFiltro;
	}
	
	public String getPesquisaFiltro() {
		return pesquisaFiltro;
	}
	
	public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}
	
	public List<Usuario> getUsuariosSelecionados() {
		return usuariosSelecionados;
	}
	
	public String getMensagemExclusao() {
		return mensagemExclusao;
	}
}
