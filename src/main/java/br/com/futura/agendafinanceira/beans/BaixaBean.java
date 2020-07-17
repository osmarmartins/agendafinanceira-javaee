package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.math.BigDecimal;
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

import br.com.futura.agendafinanceira.dto.BaixaFiltroDto;
import br.com.futura.agendafinanceira.dto.PagamentoDto;
import br.com.futura.agendafinanceira.dto.PaginacaoDto;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.services.BaixaService;
import br.com.futura.agendafinanceira.services.FornecedorService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class BaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BaixaFiltroDto filtro = new BaixaFiltroDto();
	
	private Date dataBaixa;
	
	private BigDecimal total;
	
	private LazyDataModel<PagamentoDto> parcelas;
	
	private List<PagamentoDto> parcelasSelecionadas = new ArrayList<>();
	
	private List<Fornecedor> fornecedores = new ArrayList<>();
	
	private Integer qtdRegistros;
	
	@Inject
	private MessagesHelper messagesHelper;
	
	@Inject
	private BaixaService baixaService;
	
	@Inject
	private FornecedorService fornecedorService;
	
	@PostConstruct
	private void init() {
		this.dataBaixa = new Date();
		this.filtro = new BaixaFiltroDto();
		this.total = BigDecimal.ZERO;
		this.parcelasSelecionadas = new ArrayList<>();
		this.fornecedores = fornecedorService.listarAtivos();
		filtrar();
	}
	
	public void filtrar() {
		this.parcelasSelecionadas = new ArrayList<>();
		this.qtdRegistros = baixaService.contarRegistros(filtro);
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
				
				return baixaService.listarPor(filtro);
			}
			
			@Override
			public Object getRowKey(PagamentoDto parcela) {
				return parcela.getIdParcela();
			}
			
			@Override
			public PagamentoDto getRowData(String rowKey) {
				for (PagamentoDto parcela : parcelas) {
					if (Integer.toString(parcela.getIdParcela()).equals(rowKey)) {
						return parcela;
					}
				}
				return null;
			}
		};
		
	}
	
	public String baixar(PagamentoParcela parcela) {
		return "baixacadastro?faces-redirect=true&parcela=" + parcela.getIdPagamentoParcela();
	}
	
	public void baixarSelecionadas() {
		baixaService.baixarParcelas(parcelasSelecionadas, dataBaixa);
		filtrar();
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		
	}
	
	public Boolean isExisteSelecao() {
		return !parcelasSelecionadas.isEmpty();
	}
	
	public void calcularTotal() {
		this.total = BigDecimal.ZERO;
		for (PagamentoDto parcela : this.parcelasSelecionadas) {
			this.total = this.total.add(parcela.getSaldoDevedor());
		}
	}
	
	public LazyDataModel<PagamentoDto> getParcelas() {
		return parcelas;
	}
	
	public void setParcelasSelecionadas(List<PagamentoDto> parcelasSelecionadas) {
		this.parcelasSelecionadas = parcelasSelecionadas;
	}
	
	public List<PagamentoDto> getParcelasSelecionadas() {
		return parcelasSelecionadas;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public void setFiltro(BaixaFiltroDto filtro) {
		this.filtro = filtro;
	}
	
	public BaixaFiltroDto getFiltro() {
		return filtro;
	}
	
	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}
	
	public Date getDataBaixa() {
		return dataBaixa;
	}
	
	public List<Fornecedor> getFornecedores(){
		return this.fornecedores;
	}
	
}
