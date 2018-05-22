package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.BaixaDao;
import br.com.futura.agendafinanceira.models.PagamentoParcela;

@Named
public class BaixaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BaixaDao baixaDao;
	
	public List<PagamentoParcela> listarTodos(){
		return baixaDao.listarTodos();
	}
	
	public List<PagamentoParcela> listarPor(String pesquisa) {
		return baixaDao.listarPor(pesquisa);
	}

}
