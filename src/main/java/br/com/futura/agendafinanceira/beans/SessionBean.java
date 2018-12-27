package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Usuario;

@Named
@SessionScoped
public class SessionBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer itensPorPagina;
	
	private Usuario usuarioLogado;
	
	@PostConstruct
	private void init() {
		this.itensPorPagina = 8;
	}
	
	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}
	
	public Integer getItensPorPagina() {
		return this.itensPorPagina;
	}
	
	public void login() {
		System.out.println("Login efetuado com o usuário: " + usuarioLogado);
	}
	
	public Usuario getUsuarioLogado() {
		if (this.usuarioLogado == null) {
			this.usuarioLogado = new Usuario();
		}
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
