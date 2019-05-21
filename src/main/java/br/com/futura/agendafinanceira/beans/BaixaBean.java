package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.dto.BaixaFiltroDto;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.services.BaixaService;
import br.com.futura.agendafinanceira.services.FornecedorService;

@Named
@ViewScoped
public class BaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BaixaFiltroDto filtro;
	
	private Date dataBaixa;
	
	private BigDecimal total;
	
	private List<PagamentoParcela> parcelas;
	
	private List<PagamentoParcela> parcelasSelecionadas;
	
	private List<Fornecedor> fornecedores;
	
	@Inject
	private BaixaService baixaService;
	
	@Inject
	private FornecedorService fornecedorService;
	
	@PostConstruct
	private void init() {
		this.filtro = new BaixaFiltroDto();
		this.total = BigDecimal.ZERO;
		this.parcelas = new ArrayList<>();
		this.parcelasSelecionadas = new ArrayList<>();
		this.fornecedores = fornecedorService.listarAtivos(); 
	}
	
	public void filtrar() {
		this.parcelasSelecionadas = new ArrayList<>();
		this.parcelas = baixaService.listarPor(filtro);
	}
	
	public String baixar(PagamentoParcela parcela) {
		return "baixacadastro?faces-redirect=true&parcela=" + parcela.getIdPagamentoParcela();
	}
	
	public void baixarSelecionadas() {
		System.out.println("DATA >>>>>>>>>>>> " + this.dataBaixa);
		baixaService.baixarParcelas(parcelasSelecionadas);
		filtrar();
	}
	
	public Boolean isExisteSelecao() {
		return !parcelasSelecionadas.isEmpty();
	}
	
	public void calcularTotal() {
		this.total = BigDecimal.ZERO;
		for (PagamentoParcela parcela : this.parcelasSelecionadas) {
			this.total = this.total.add(parcela.saldoDevedor());
		}
	}
	
	public List<PagamentoParcela> getParcelas() {
		return parcelas;
	}
	
	public void setParcelasSelecionadas(List<PagamentoParcela> parcelasSelecionadas) {
		this.parcelasSelecionadas = parcelasSelecionadas;
	}
	
	public List<PagamentoParcela> getParcelasSelecionadas() {
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
