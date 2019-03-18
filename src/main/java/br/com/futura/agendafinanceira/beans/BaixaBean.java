package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.services.BaixaService;

@Named
@ViewScoped
public class BaixaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String pesquisaFiltro;
	
	private List<PagamentoParcela> parcelas;
	
	@Inject
	private BaixaService baixaService;
	
	private List<PagamentoParcela> parcelasSelecionadas;
	
	private String mensagemExclusao;
	
	@PostConstruct
	private void init() {
		this.parcelas = baixaService.listarTodos();
		this.parcelasSelecionadas = new ArrayList<>();
		this.mensagemExclusao = new String();
	}
	
	public String alterar(PagamentoParcela parcela) {
		return "baixacadastro?faces-redirect=true&parcela=" + parcela.getIdPagamentoParcela();
	}
	
	
	public Boolean isExisteSelecao() {
		return !parcelasSelecionadas.isEmpty();
	}
	
	public void selecionaParcela(PagamentoParcela parcela) {
		parcelasSelecionadas.add(parcela);
		mensagemExclusaoBuilder();
	}
	
	public void mensagemExclusaoBuilder() {
		StringBuilder msg = new StringBuilder();

		if (this.getParcelas()!=null && !this.getParcelasSelecionadas().isEmpty()) {
			msg.append("Excluir permanentemente ");
			if (this.getParcelasSelecionadas().size()>1) {
				msg.append("os pagamentos selecionados?");
			}else {
				msg.append("o pagamento ");
				msg.append(parcelasSelecionadas.get(0).getPagamento().getHistorico() + 
						" parcela " + 
						parcelasSelecionadas.get(0).getParcela());
			}
		}
		this.mensagemExclusao = msg.toString();
	}

	
	public void excluir() {
		//TODO excluir baixa 
	}
	
	public void pesquisar(){
		this.parcelas = baixaService.listarPor(this.pesquisaFiltro);
	}

	public void setPesquisaFiltro(String pesquisaFiltro) {
		this.pesquisaFiltro = pesquisaFiltro;
	}
	
	public String getPesquisaFiltro() {
		return pesquisaFiltro;
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
	
	public void setMensagemExclusao(String mensagemExclusao) {
		this.mensagemExclusao = mensagemExclusao;
	}
	
	public String getMensagemExclusao() {
		return mensagemExclusao;
	}

}
