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
	
	private String opcaoMenuAtiva;
	
	private String grupoMenuAtivo;
	
	@PostConstruct
	private void init() {
		this.itensPorPagina = 8;
		this.opcaoMenuAtiva = "";
		this.grupoMenuAtivo = "";
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

	
	public String getGrupoMenuAtivo() {
		return grupoMenuAtivo;
	}
	
	public String getOpcaoMenuAtiva() {
		return opcaoMenuAtiva;
	}
	
	public String opcaoMenuAtiva(String opcao) {
		if (opcao.equals(this.opcaoMenuAtiva)) {
			return "active";
		}
		return "";
	}

	public String grupoMenuAtivo(String grupo) {
		if (grupo.equals(this.grupoMenuAtivo)) {
			return "active open";
		}
		return "";
	}
		
	public void mudarOpcaoMenu(String opcao) {
		this.opcaoMenuAtiva = opcao;
	}
	
	public void mudarGrupoMenu(String grupo ) {
		this.grupoMenuAtivo = grupo;
	}
	
}
