package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.models.Tema;

@ManagedBean
@SessionScoped
public class TemaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tema temaSessao;
	
	@PostConstruct
	private void init() {
		this.temaSessao.setTema("bootstrap");
		this.temaSessao.setEstilo("no-skin");
		this.temaSessao.setMargensAtivas(false);
		this.temaSessao.setMenuCompacto(false);
		this.temaSessao.setSubmenuFlutuante(false);
		this.temaSessao.setDestaqueSelecao(false);
	}
	

	public Tema getTemaSessao() {
		return temaSessao;
	}
	
	public void setTemaSessao(Tema temaSessao) {
		this.temaSessao = temaSessao;
	}
	
	public void mudarTema() {

	}

}
