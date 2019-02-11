package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.PagamentoParcelaDao;
import br.com.futura.agendafinanceira.dto.ParcelamentoDto;
import br.com.futura.agendafinanceira.dto.RelatorioFiltroDto;
import br.com.futura.agendafinanceira.exceptions.NenhumResultadoException;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;

public class PagamentoParcelaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PagamentoParcelaDao parcelaDao;

	public PagamentoParcela pesquisaPorId(Integer idParcela) {
		return parcelaDao.pesquisaPorId(idParcela);
	}

	public List<PagamentoParcela> listarPor(RelatorioFiltroDto filtro) {
		List<PagamentoParcela> lista = parcelaDao.listarPor(filtro);
		if (lista.isEmpty()) {
			throw new NenhumResultadoException("Nenhum registro encontrado!");
		} else {
			return parcelaDao.listarPor(filtro);
		}
	}

	@Transactional
	public void gerarParcelamento(ParcelamentoDto parcelamento) {


		for (Integer numeroParcela = 1; numeroParcela <= parcelamento.getQuantidadeParcelas(); numeroParcela++) {
			PagamentoParcela parcela = new PagamentoParcela();
			parcela.setPagamento(parcelamento.getPagamento());
			parcela.setParcela(parcelamento.obterParcela(numeroParcela));
			parcela.setVencimento(parcelamento.calcularVencimento(numeroParcela));
			parcela.setValor(parcelamento.getValorParcela());
//			parcela.setDesconto(BigDecimal.ZERO);
//			parcela.setJuros(BigDecimal.ZERO);
//			parcela.setMora(BigDecimal.ZERO);
//			parcela.setOutros(BigDecimal.ZERO);
//			parcela.setSituacao(SituacaoParcela.NOVO);
			
			parcelamento.getPagamento().addParcela(parcela);
			
			parcelaDao.salvar(parcela);
		}
	}

	@Transactional
	public void salvar(Pagamento pagamento, PagamentoParcela parcela) {
		pagamento.addParcela(parcela);
		parcelaDao.salvar(parcela);
	}

	@Transactional
	public void excluir(PagamentoParcela parcela) {
		// TODO validar exclusão (não permitir excluir parcela com registro de quitação)
		parcelaDao.excluir(parcela);
		parcela.getPagamento().removeParcela(parcela);
	}

}
