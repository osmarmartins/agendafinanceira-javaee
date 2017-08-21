package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.UsuarioDao;
import br.com.futura.agendafinanceira.models.Usuario;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private MessagesHelper messagesHelper;
	
	@PostConstruct
	private void init(){
		usuario = new Usuario();
		this.usuarios = usuarioDao.listarTodos(); 
	}
	
	public String alterar(Usuario usuario){
		return "/usuariocadastro?faces-redirect=true&usuario=" + usuario.getIdUsuario();
	}
	
	public void excluir(Usuario usuario){
		usuarioDao.excluir(usuario);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
}
