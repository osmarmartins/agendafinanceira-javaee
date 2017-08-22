package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.UsuarioDao;
import br.com.futura.agendafinanceira.models.Usuario;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.models.enums.TipoUsuario;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Model
public class UsuarioCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private String confirmarSenha;

	private TipoUsuario[] tiposUsuario;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.usuario = new Usuario();
		this.usuario.setAtivo(Ativo.ATIVO);
		this.confirmarSenha = usuario.getSenha();
		this.tiposUsuario = TipoUsuario.values();
	}

	public void salvar() {
		if (this.usuario.getIdUsuario() == null && !this.usuario.getSenha().equals(this.confirmarSenha)) {
			messagesHelper.addFlash(new FacesMessage("Senhas não conferem!"));
			return;
		}
		try {
			usuarioDao.salvar(usuario);
			messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso!"));
			init();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public TipoUsuario[] getTiposUsuario() {
		return tiposUsuario;
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
