package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer itensPorPagina;
	
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

}
