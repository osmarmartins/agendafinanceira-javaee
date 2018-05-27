package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;
import br.com.futura.agendafinanceira.services.BaixaService;

@Named
@ViewScoped
public class BaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String pesquisa;
	
	private List<PagamentoParcela> parcelas;
	
	@Inject
	private BaixaService baixaService;
	
	@PostConstruct
	private void init() {
		this.parcelas = baixaService.listarTodos();
	}
	
	public String alterar(PagamentoParcela parcela) {
		return "baixacadastro?faces-redirect=true&parcela=" + parcela.getIdPagamentoParcela();
	}
	
	public void excluir(PagamentoParcela parcela) {
		//TODO excluir baixa 
	}
	
	public void pesquisar(){
		this.parcelas = baixaService.listarPor(this.pesquisa);
	}
	
	public String getPesquisa() {
		return pesquisa;
	}
	
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	public List<PagamentoParcela> getParcelas() {
		return parcelas;
	}

}
