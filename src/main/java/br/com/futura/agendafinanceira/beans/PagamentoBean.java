package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.futura.agendafinanceira.dto.PagamentoDto;
import br.com.futura.agendafinanceira.dto.PagamentoFiltroDto;
import br.com.futura.agendafinanceira.dto.PaginacaoDto;
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
	
	private LazyDataModel<PagamentoDto> parcelas;

	private List<PagamentoDto> parcelasSelecionadas;
	
	private List<Fornecedor> fornecedores;
	
	private SituacaoParcela[] situacao;
	
	private String mensagemExclusao;
	
	private PagamentoFiltroDto filtro = new PagamentoFiltroDto();
	
	private Date dataProgramacao;
	
	private Integer qtdRegistros;
	
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
		this.parcelasSelecionadas = new ArrayList<>();
		this.mensagemExclusao = new String();
		filtrar();
	}
	
	public void filtrar() {
		this.parcelasSelecionadas = new ArrayList<>();
		this.qtdRegistros = pagamentoService.contarRegistros(filtro);
		this.parcelas = new LazyDataModel<PagamentoDto>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<PagamentoDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				
				PaginacaoDto paginacao = filtro.getPaginacao();
							
				paginacao.setPagina(first);
				paginacao.setRegistrosPorPagina(pageSize);
				paginacao.setOrdenacao(sortField);
				paginacao.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				
				setRowCount(qtdRegistros);
				
				return pagamentoService.listarPor(filtro);
			}			
		}; 
	}

	public void aplicarDataProgramacao() {
		pagamentoService.aplicarDataProgramacao(parcelasSelecionadas, dataProgramacao);
		filtrar();
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
	}

	public void agendarPagamento() {
		pagamentoService.agendarPagamento(parcelasSelecionadas);
		filtrar();
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
	}

	public String alterar(Pagamento pagamento) {
		return "/pagamento/pagamentocadastro?faces-redirect=true&pagamento=" + pagamento.getIdPagamento();
	}
	
	public Boolean isExisteSelecao() {
		return !parcelasSelecionadas.isEmpty();
	}
	
	public void selecionarParcela(PagamentoDto parcela) {
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
				msg.append(parcelasSelecionadas.get(0).getHistorico());
			}
		}
		this.mensagemExclusao = msg.toString();
	}

	public void excluir() {
		pagamentoService.excluir(parcelasSelecionadas);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public LazyDataModel<PagamentoDto> getParcelas() {
		return parcelas;
	}
	
	public List<PagamentoDto> getParcelasSelecionadas() {
		return parcelasSelecionadas;
	}

	public void setParcelasSelecionadas(List<PagamentoDto> parcelasSelecionadas) {
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
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
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
