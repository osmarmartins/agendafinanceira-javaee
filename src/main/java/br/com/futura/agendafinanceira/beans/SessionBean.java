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
	
	private String estilo;
	
	private String tema;
	
	private String margens;
	
	private String submenuFlutuante;
	
	private String menuCompacto;
	
	private String opcaoSelecionada;
	
	@PostConstruct
	private void init() {
		this.setTema("bootstrap");
		this.itensPorPagina = 8;
		this.estilo="no-skin";
		this.margens="";
		this.submenuFlutuante="";
	}
	
	public String[] getTemas() {
		return new String[] {"admin", "afterdark", "afternoon", "afterwork", "aristo", "black-tie", "blitzer", "bluesky",
				"bootstrap", "casablanca", "cupertino", "cruze", "dark-hive", "delta", "dot-luv", "eggplant",
				"excite-bike", "flick", "glass-x", "home", "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
				"overcast", "pepper-grinder", "redmond", "rocket", "sam", "smoothness", "south-street", "start",
				"sunny", "swanky-purse", "trontastic", "ui-darkness", "ui-lightness", "vader" };
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

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	
	public String getEstilo() {
		return estilo;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getMargens() {
		return margens;
	}

	public void setMargens(String margens) {
		if (this.margens.equals("")) {
			this.margens = "container";
		} else {
			this.margens = "";
		}
	}

	public String getSubmenuFlutuante() {
		return submenuFlutuante;
	}

	public void setSubmenuFlutuante(String submenuFlutuante) {
		if (this.submenuFlutuante.equals("")) { 
			this.submenuFlutuante = "hover";
		} else {
			this.submenuFlutuante = "";
		}
	}

	public String getMenuCompacto() {
		return menuCompacto;
	}

	public void setMenuCompacto(String menuCompacto) {
		if (this.menuCompacto.equals("")) {
			this.menuCompacto = "compact";
		} else {
			this.menuCompacto = "";
		}
	}

	public String getOpcaoSelecionada() {
		return opcaoSelecionada;
	}

	public void setOpcaoSelecionada(String opcaoSelecionada) {
		this.opcaoSelecionada = opcaoSelecionada;
	}
	
	
	
}
