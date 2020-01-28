package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.futura.agendafinanceira.models.Usuario;
import br.com.futura.agendafinanceira.models.UsuarioPreferencias;
import br.com.futura.agendafinanceira.services.UsuarioService;

@Named
@SessionScoped
public class SessionBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioLogado;
	private Integer itensPorPagina;
	private String tema;
	private String estilo;
	private String margens;
	private String submenuFlutuante;
	private String menuCompacto;
	
	@Inject
	private UsuarioService usuarioService;
	
	@PostConstruct
	private void init() {
		this.itensPorPagina = 8;
		this.tema = "bootstrap";
		this.estilo = "no-skin";
		this.margens = "";
		this.submenuFlutuante = "";
		this.menuCompacto = "";
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
	
	public Usuario getUsuarioLogado() {
		if (this.usuarioLogado == null) {
			this.usuarioLogado = new Usuario();
		}
		return usuarioLogado;
	}

	private void carregarPreferencias() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String nome = "";    
		
		if (principal instanceof UserDetails) {
			nome = ((UserDetails)principal).getUsername();
		} else {
			nome = principal.toString();
		}
		
		if (nome.equals("anonymousUser")) {
			return;
		}
		
		this.usuarioLogado = usuarioService.loadUserByUsername(nome);
		
		UsuarioPreferencias preferencias;
		preferencias = this.usuarioLogado.getPreferencias();
		
		if (preferencias != null) {
			this.tema = preferencias.getTema();
			this.estilo = preferencias.getEstilo();
			this.margens = preferencias.getMargens();
			this.submenuFlutuante = preferencias.getSubmenuFlutuante();
			this.menuCompacto = preferencias.getMenuCompacto();
		}else {
			preferencias = new UsuarioPreferencias();
			preferencias.setId(usuarioLogado.getIdUsuario());
			preferencias.setEstilo(estilo);
			preferencias.setTema(tema);
			preferencias.setMargens(margens);
			preferencias.setSubmenuFlutuante(submenuFlutuante);
			preferencias.setMenuCompacto(menuCompacto);
			this.usuarioLogado.setPreferencias(preferencias);
			usuarioService.salvar(usuarioLogado);
		}
	}
	
	private void salvarPreferencias() {
		if (this.usuarioLogado.getIdUsuario() == null) {
			return;
		}
		usuarioService.salvar(usuarioLogado);
	}

	public String getEstilo() {
		if (this.usuarioLogado.getIdUsuario() == null) {
			carregarPreferencias();
		}
		return estilo;
	}

	public void setEstilo(String estilo) {
		if (!usuarioLogado.getPreferencias().getEstilo().equals(estilo)) {
			usuarioLogado.getPreferencias().setEstilo(estilo);
			salvarPreferencias();
		}
		this.estilo = estilo;
	}
	
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		if (!usuarioLogado.getPreferencias().getTema().equals(tema)) {
			usuarioLogado.getPreferencias().setTema(tema);
			salvarPreferencias();
		}
		this.tema = tema;
	}

	public void alternarMargens() {
		if (this.margens.equals("")) {
			this.margens = "container";
		} else {
			this.margens = "";
		}
		this.usuarioLogado.getPreferencias().setMargens(this.margens);
		salvarPreferencias();
	}

	public void alternarSubmenuFlutuante() {
		if (this.submenuFlutuante.equals("")) { 
			this.submenuFlutuante = "hover";
		} else {
			this.submenuFlutuante = "";
		}
		this.usuarioLogado.getPreferencias().setSubmenuFlutuante(this.submenuFlutuante);
		salvarPreferencias();
	}

	public void alternarMenuCompacto() {
		if (this.menuCompacto.equals("")) {
			this.menuCompacto = "compact";
			this.submenuFlutuante = "hover";
			this.usuarioLogado.getPreferencias().setSubmenuFlutuante("hover");
		} else {
			this.menuCompacto = "";
		}
		this.usuarioLogado.getPreferencias().setMenuCompacto(this.menuCompacto);
		salvarPreferencias();
	}
	
	public String statusMargens() {
		return this.margens.equals("container") ? "INATIVAR Margens" : "ATIVAR Margens";
	}
	
	public String statusSubmenuFlutuante() {
		return this.submenuFlutuante.equals("hover") ? "INATIVAR Submenu Flutuante" : "ATIVAR Submenu Flutuante";
	}
	
	public String statusMenuCompacto() {
		return this.menuCompacto.equals("compact") ? "INATIVAR Menu Compacto" : "ATIVAR Menu Compacto";
	}
	
	public String getMenuCompacto() {
		return menuCompacto;
	}

	public String getSubmenuFlutuante() {
		return submenuFlutuante;
	}

	public String getMargens() {
		return margens;
	}
	
}
