package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.daos.PagamentoParcelaDao;
import br.com.futura.agendafinanceira.dto.ParcelamentoDto;
import br.com.futura.agendafinanceira.dto.RelatorioFiltroDto;
import br.com.futura.agendafinanceira.exceptions.ApplicationException;
import br.com.futura.agendafinanceira.exceptions.NenhumResultadoException;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;
import br.com.futura.agendafinanceira.models.enums.FormaPagamento;
import br.com.futura.agendafinanceira.models.enums.TipoLancamento;

public class PagamentoParcelaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PagamentoParcelaDao parcelaDao;
	
	@Inject
	private PagamentoDao pagamentoDao;
	
	@Inject
	private BaixaParcelaService baixaService;

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
		if (parcelamento.getParcelaInicial() > parcelamento.getQuantidadeParcelas()) {
			throw new ApplicationException("Parcela inicial não pode ser maior qua a quantidade de parcelas");
		}
		
		if (TipoLancamento.MENSAL_LANCAMENTO_VARIAS_PARCELAS.equals(parcelamento.getTipoLancamento()) )
			gerarVariasParcelasPorLancamento(parcelamento);
		
		if (TipoLancamento.MENSAL_LANCAMENTO_UMA_PARCELA.equals(parcelamento.getTipoLancamento()) )
			gerarUmaParcelasPorLancamento(parcelamento);
		
		if (TipoLancamento.INTERVALO_DIAS.equals(parcelamento.getTipoLancamento()) )
			gerarParcelasPorIntervaloDias(parcelamento);
		
	}

	private void gerarParcelasPorIntervaloDias(ParcelamentoDto parcelamento) {
		for (Integer numeroParcela = parcelamento.getParcelaInicial(); numeroParcela <= parcelamento.getQuantidadeParcelas(); numeroParcela++) {
			
			PagamentoParcela parcela = new PagamentoParcela();
			parcela.setParcela(parcelamento.obterParcela(numeroParcela));
			parcela.setVencimento(parcelamento.calcularVencimentoPorIntervaloDias(numeroParcela, parcelamento.getParcelaInicial()));
			parcela.setValor(parcelamento.getValorParcela());
			
			parcelamento.getPagamento().addParcela(parcela);
			parcelaDao.salvar(parcela);
		}
	}

	private void gerarUmaParcelasPorLancamento(ParcelamentoDto parcelamento) {
		Pagamento pagamento;
		for (Integer numeroParcela = parcelamento.getParcelaInicial(); numeroParcela <= parcelamento.getQuantidadeParcelas(); numeroParcela++) {
			PagamentoParcela parcela = prepararParcela(parcelamento, numeroParcela);

			if (numeroParcela == 1) {
				pagamento = parcelamento.getPagamento();
			}else {
				pagamento = new Pagamento( parcelamento.getPagamento() );
				pagamentoDao.salvar(pagamento);
			}

			pagamento.addParcela(parcela);
			parcela.setPagamento(pagamento);

			parcelaDao.salvar(parcela);
		}
	}

	private void gerarVariasParcelasPorLancamento(ParcelamentoDto parcelamento) {
		for (Integer numeroParcela = parcelamento.getParcelaInicial(); numeroParcela <= parcelamento.getQuantidadeParcelas(); numeroParcela++) {
			PagamentoParcela parcela = prepararParcela(parcelamento, numeroParcela);
			parcelamento.getPagamento().addParcela(parcela);
			parcelaDao.salvar(parcela);
		}
	}

	private PagamentoParcela prepararParcela(ParcelamentoDto parcelamento, Integer numeroParcela) {
		PagamentoParcela parcela = new PagamentoParcela();
		parcela.setParcela(parcelamento.obterParcela(numeroParcela));
		parcela.setVencimento(parcelamento.calcularVencimento(numeroParcela, parcelamento.getParcelaInicial()));
		parcela.setValor(parcelamento.getValorParcela());
		return parcela;
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

	@Transactional
	public PagamentoParcela liquidar(PagamentoParcela parcela) {
		PagamentoQuitacao quitacao = new PagamentoQuitacao(parcela.getValor(), parcela.getVencimento(), FormaPagamento.DINHEIRO);
		baixaService.salvar(parcela, quitacao);
		return parcela;
	}

}
