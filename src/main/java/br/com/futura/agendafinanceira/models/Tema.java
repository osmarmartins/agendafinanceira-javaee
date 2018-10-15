package br.com.futura.agendafinanceira.models;

import java.io.Serializable;

public class Tema implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tema;
	private String estilo;
	private Boolean margensAtivas;
	private Boolean submenuFlutuante;
	private Boolean menuCompacto;
	private Boolean destaqueSelecao;
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getEstilo() {
		return estilo;
	}
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	public Boolean getMargensAtivas() {
		return margensAtivas;
	}
	public void setMargensAtivas(Boolean margensAtivas) {
		this.margensAtivas = margensAtivas;
	}
	public Boolean getSubmenuFlutuante() {
		return submenuFlutuante;
	}
	public void setSubmenuFlutuante(Boolean submenuFlutuante) {
		this.submenuFlutuante = submenuFlutuante;
	}
	public Boolean getMenuCompacto() {
		return menuCompacto;
	}
	public void setMenuCompacto(Boolean menuCompacto) {
		this.menuCompacto = menuCompacto;
	}
	public Boolean getDestaqueSelecao() {
		return destaqueSelecao;
	}
	public void setDestaqueSelecao(Boolean destaqueSelecao) {
		this.destaqueSelecao = destaqueSelecao;
	}
	
	public String[] getTemas() {
		return new String[] {"admin", "afterdark", "afternoon", "afterwork", "aristo", "black-tie", "blitzer", "bluesky",
				"bootstrap", "casablanca", "cupertino", "cruze", "dark-hive", "delta", "dot-luv", "eggplant",
				"excite-bike", "flick", "glass-x", "home", "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
				"overcast", "pepper-grinder", "redmond", "rocket", "sam", "smoothness", "south-street", "start",
				"sunny", "swanky-purse", "trontastic", "ui-darkness", "ui-lightness", "vader" };
	}
	@Override
	public String toString() {
		return "Tema [tema=" + tema + ", estilo=" + estilo + ", margensAtivas=" + margensAtivas + ", submenuFlutuante="
				+ submenuFlutuante + ", menuCompacto=" + menuCompacto + ", destaqueSelecao=" + destaqueSelecao + "]";
	}
	
	

}
