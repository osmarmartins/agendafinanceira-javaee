package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Autorizacao;
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
	
	private Autorizacao novaAutorizacao = new Autorizacao();

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private MessagesHelper messagesHelper;

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
		return "usuario?faces-redirect";
	}
	
	public void adicionarAutorizacao() {
		usuarioService.adicionarAutorizacao(usuario, novaAutorizacao);
	}
	
	public void removerAutorizacao(Autorizacao autorizacao) {
		usuarioService.removerAutorizacao(usuario, autorizacao);
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
		if (this.usuario == null) {
			this.usuario = new Usuario();
			this.usuario.setAtivo(Ativo.ATIVO);
		}
		return this.usuario;
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
	
	public List<Autorizacao> autorizacoes() {
		return usuarioService.listarAutorizacoesDisponiveis(this.usuario);
	}
	
	public void setNovaAutorizacao(Autorizacao novaAutorizacao) {
		this.novaAutorizacao = novaAutorizacao;
	}
	
	public Autorizacao getNovaAutorizacao() {
		return novaAutorizacao;
	}

}
