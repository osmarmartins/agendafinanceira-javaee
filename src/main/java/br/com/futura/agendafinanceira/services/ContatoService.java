package br.com.futura.agendafinanceira.services;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.ContatoDao;
import br.com.futura.agendafinanceira.models.Contato;

public class ContatoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContatoDao contatoDao;

	public void salvar(Contato contato) {
		contatoDao.salvar(contato);
	}

	public void excluir(Contato contato) {
		contatoDao.excluir(contato);
		
	}
	
	

}
