package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.BaixaDao;
import br.com.futura.agendafinanceira.dto.BaixaFiltroDto;
import br.com.futura.agendafinanceira.dto.RelatorioFiltroDto;
import br.com.futura.agendafinanceira.exceptions.NenhumResultadoException;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;

public class BaixaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BaixaDao baixaDao;
	
	public List<PagamentoParcela> listarPor(BaixaFiltroDto filtro) {
		return baixaDao.listarPor(filtro);
	}	
	
	public List<PagamentoQuitacao> listarPor(RelatorioFiltroDto filtro){
		List<PagamentoQuitacao> lista = baixaDao.listarPor(filtro);
		if (lista.isEmpty()) {
			throw new NenhumResultadoException("Nenhum registro encontrado!");
		}else {
			return lista;
		}
	}

	public void baixarParcelas(List<PagamentoParcela> parcelas) {
		for (PagamentoParcela parcela : parcelas) {
			// TODO Usar o método baixa (VERIFICAR RESPONSABILIDADES DE CADA CLASSE)
		}
		
	}

}
