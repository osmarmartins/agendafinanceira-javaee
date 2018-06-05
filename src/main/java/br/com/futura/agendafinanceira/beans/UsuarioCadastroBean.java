package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Usuario;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoUsuario;
import br.com.futura.agendafinanceira.services.UsuarioService;
import br.com.futura.agendafinanceira.utils.HashMD5Util;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class UsuarioCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private String confirmarSenha;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.usuario = new Usuario();
		this.usuario.setAtivo(Ativo.ATIVO);
	}

	public String salvar() {
		try {
			if (this.usuario.getIdUsuario() == null) {
				this.usuario.setSenha(HashMD5Util.getMD5(this.usuario.getSenha()));
			}
			usuarioService.salvar(usuario);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso!"));
		init();
		return "usuariocadastro?faces-redirect";
	}

	public TipoUsuario[] getTiposUsuario() {
		return TipoUsuario.values();
	}
	
	public boolean getHasError() {
		return false;
	}
	
	public boolean isNovoUsuario() {
		return this.usuario.getIdUsuario()==null;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			init();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

}
