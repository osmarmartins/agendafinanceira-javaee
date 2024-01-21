package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.AgrupamentoDao;
import br.com.futura.agendafinanceira.models.Agrupamento;

public class AgrupamentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AgrupamentoDao agrupamentoDao;
	
	public List<Agrupamento> listarTodos(){
		return agrupamentoDao.listarTodos();
	}

}
