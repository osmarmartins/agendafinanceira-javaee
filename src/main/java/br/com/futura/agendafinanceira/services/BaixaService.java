package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.BaixaDao;
import br.com.futura.agendafinanceira.dto.BaixaFiltroDto;
import br.com.futura.agendafinanceira.dto.PagamentoDto;
import br.com.futura.agendafinanceira.dto.RelatorioFiltroDto;
import br.com.futura.agendafinanceira.exceptions.NenhumResultadoException;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;
import br.com.futura.agendafinanceira.models.enums.FormaPagamento;

public class BaixaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BaixaDao baixaDao;
	
	@Inject
	private BaixaParcelaService baixaParcelaService;
	
	public List<PagamentoDto> listarPor(BaixaFiltroDto filtro) {
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

	@Transactional
	public void baixarParcelas(List<PagamentoParcela> parcelas, Date data) {
		PagamentoQuitacao quitacao;
		for (PagamentoParcela parcela : parcelas) {
			quitacao = new PagamentoQuitacao(parcela.saldoDevedor(), data, FormaPagamento.OUTROS);
			baixaParcelaService.salvar(parcela, quitacao);
		}
	}

}
