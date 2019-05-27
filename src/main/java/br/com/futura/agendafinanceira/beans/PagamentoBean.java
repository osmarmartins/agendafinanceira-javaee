package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.dto.PagamentoFiltroDto;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;
import br.com.futura.agendafinanceira.services.FornecedorService;
import br.com.futura.agendafinanceira.services.PagamentoService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<PagamentoParcela> parcelas;

	private List<PagamentoParcela> parcelasSelecionadas;
	
	private List<Fornecedor> fornecedores;
	
	private SituacaoParcela[] situacao;
	
	private String mensagemExclusao;
	
	private PagamentoFiltroDto filtro;
	
	private Date dataProgramacao;
	
	@Inject
	private PagamentoService pagamentoService;
	
	@Inject
	private FornecedorService fornecedorService;

	@Inject
	private MessagesHelper messagesHelper;
	
	@PostConstruct
	private void init() {
		this.fornecedores = fornecedorService.listarTodos();
		this.situacao = SituacaoParcela.values();

		this.filtro = new PagamentoFiltroDto();
		
		this.parcelas = new ArrayList<>();
		this.parcelasSelecionadas = new ArrayList<>();
		
		this.mensagemExclusao = new String();
	}
	
	public void aplicarDataProgramacao() {
		pagamentoService.aplicarDataProgramacao(parcelasSelecionadas, dataProgramacao);
	}

	public void agendarPagamento() {
		pagamentoService.agendarPagamento(parcelasSelecionadas);
	}

	public String alterar(Pagamento pagamento) {
		return "/pagamento/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}
	
	public Boolean isExisteSelecao() {
		return !parcelasSelecionadas.isEmpty();
	}
	
	public void selecionarParcela(PagamentoParcela parcela) {
		parcelasSelecionadas.add(parcela);
		mensagemExclusaoBuilder();
	}
	
	public void mensagemExclusaoBuilder() {
		StringBuilder msg = new StringBuilder();

		if (this.getParcelasSelecionadas()!=null && !this.getParcelasSelecionadas().isEmpty()) {
			msg.append("Excluir permanentemente ");
			if (this.getParcelasSelecionadas().size()>1) {
				msg.append("os pagamentos selecionados?");
			}else {
				msg.append("o pagamento ");
				msg.append(parcelasSelecionadas.get(0).getPagamento().getHistorico());
			}
		}
		this.mensagemExclusao = msg.toString();
	}

	public void excluir() {
		pagamentoService.excluir(parcelasSelecionadas);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public void filtrar() {
		this.parcelas = pagamentoService.listarPor(filtro);
	}

	public List<PagamentoParcela> getParcelas() {
		return parcelas;
	}
	
	public List<PagamentoParcela> getParcelasSelecionadas() {
		return parcelasSelecionadas;
	}

	public void setParcelasSelecionadas(List<PagamentoParcela> parcelasSelecionadas) {
		this.parcelasSelecionadas = parcelasSelecionadas;
	}

	public String getMensagemExclusao() {
		return mensagemExclusao;
	}
	
	public void setFiltro(PagamentoFiltroDto filtro) {
		this.filtro = filtro;
	}
	
	public PagamentoFiltroDto getFiltro() {
		return filtro;
	}
	
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void setSituacao(SituacaoParcela[] situacao) {
		this.situacao = situacao;
	}
	
	public SituacaoParcela[] getSituacao() {
		return situacao;
	}
	
	public void setDataProgramacao(Date dataProgramacao) {
		this.dataProgramacao = dataProgramacao;
	}
	
	public Date getDataProgramacao() {
		return dataProgramacao;
	}
	
}
