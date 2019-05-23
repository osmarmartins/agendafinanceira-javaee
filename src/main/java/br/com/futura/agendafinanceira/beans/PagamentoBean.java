package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.dto.PagamentoFiltroDto;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.Pagamento;
import br.com.futura.agendafinanceira.models.enums.SituacaoParcela;
import br.com.futura.agendafinanceira.services.FornecedorService;
import br.com.futura.agendafinanceira.services.PagamentoService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Pagamento> pagamentos;

	private List<Pagamento> pagamentosSelecionados;
	
	private List<Fornecedor> fornecedores;
	
	private SituacaoParcela[] situacao;
	
	private String mensagemExclusao;
	
	private PagamentoFiltroDto filtro;
	
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
		
		this.pagamentos = new ArrayList<>();
		this.pagamentosSelecionados = new ArrayList<>();
		
		this.mensagemExclusao = new String();
	}

	public String alterar(Pagamento pagamento) {
		return "/pagamento/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}
	
	public Boolean isExisteSelecao() {
		return !pagamentosSelecionados.isEmpty();
	}
	
	public void selecionaPagamento(Pagamento pagamento) {
		pagamentosSelecionados.add(pagamento);
		mensagemExclusaoBuilder();
	}
	
	public void mensagemExclusaoBuilder() {
		StringBuilder msg = new StringBuilder();

		if (this.getPagamentosSelecionados()!=null && !this.getPagamentosSelecionados().isEmpty()) {
			msg.append("Excluir permanentemente ");
			if (this.getPagamentosSelecionados().size()>1) {
				msg.append("os pagamentos selecionados?");
			}else {
				msg.append("o pagamento ");
				msg.append(pagamentosSelecionados.get(0).getHistorico());
			}
		}
		this.mensagemExclusao = msg.toString();
	}

	public void excluir() {
		pagamentoService.excluir(pagamentosSelecionados);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public void filtrar() {
		System.out.println("FILTRO >>>>>>>>>>>>>> " + filtro);
//		this.pagamentos = pagamentoService.listarTodos(filtro);
		
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	
	public List<Pagamento> getPagamentosSelecionados() {
		return pagamentosSelecionados;
	}

	public void setPagamentosSelecionados(List<Pagamento> pagamentosSelecionados) {
		this.pagamentosSelecionados = pagamentosSelecionados;
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
	
}
