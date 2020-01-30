package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.PagamentoDao;
import br.com.futura.agendafinanceira.daos.PagamentoParcelaDao;
import br.com.futura.agendafinanceira.dto.PagamentoDto;
import br.com.futura.agendafinanceira.dto.PagamentoFiltroDto;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;

@Named
public class PagamentoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PagamentoDao pagamentoDao;
	
	@Inject
	private PagamentoParcelaDao parcelaDao;

	public Pagamento pesquisarPorId(Integer idPagamento) {
		return pagamentoDao.pesquisarPorId(idPagamento);
	}

	public List<PagamentoDto> listarPor(PagamentoFiltroDto filtro) {
		return pagamentoDao.listarPor(filtro);
	}

	@Transactional
	public void salvar(Pagamento pagamento) {
		pagamentoDao.salvar(pagamento);
	}

	@Transactional
	public void excluir(List<PagamentoDto> parcelasDto) {
		// TODO: Validar exclusão dos pagamentos 
		List<PagamentoParcela> parcelas = new ArrayList<>();
		for (PagamentoDto parcelaDto : parcelasDto) {
			PagamentoParcela parcela = new PagamentoParcela();
			parcela.setIdPagamentoParcela(parcelaDto.getIdParcela());
			parcelas.add(parcela);
		}
		pagamentoDao.excluir(parcelas);
	}

	@Transactional
	public void agendarPagamento(List<PagamentoDto> parcelasSelecionadas) {
		for (PagamentoDto parcelaDto : parcelasSelecionadas) {
			PagamentoParcela parcela = parcelaDao.pesquisaPorId(parcelaDto.getIdParcela());
			parcela.setSituacao(SituacaoParcela.AGENDADO);
			parcelaDao.salvar(parcela);
		}
	}

	@Transactional
	public void aplicarDataProgramacao(List<PagamentoDto> parcelasSelecionadas, Date dataProgramacao) {
		for (PagamentoDto parcelaDto : parcelasSelecionadas) {
			PagamentoParcela parcela = parcelaDao.pesquisaPorId(parcelaDto.getIdParcela());
			parcela.setVencimento(dataProgramacao);
			parcela.setSituacao(SituacaoParcela.PROGRAMADO);
			parcelaDao.salvar(parcela);
		}
	}

}
