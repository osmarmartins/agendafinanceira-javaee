package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.BaixaDao;
import br.com.futura.agendafinanceira.dto.RelatorioFiltroDto;
import br.com.futura.agendafinanceira.exceptions.NenhumResultadoException;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

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

	
	public List<PagamentoQuitacao> listarPor(RelatorioFiltroDto filtro){
		List<PagamentoQuitacao> lista = baixaDao.listarPor(filtro);
		if (lista.isEmpty()) {
			throw new NenhumResultadoException("Nenhum registro encontrado!");
		}else {
			return baixaDao.listarPor(filtro);
		}
	}	

}
