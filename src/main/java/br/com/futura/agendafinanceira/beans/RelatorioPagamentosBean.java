package br.com.futura.agendafinanceira.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.dto.RelatorioFiltroDto;
import br.com.futura.agendafinanceira.exceptions.NenhumResultadoException;
import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.services.ContaService;
import br.com.futura.agendafinanceira.services.FornecedorService;
import br.com.futura.agendafinanceira.services.PagamentoParcelaService;
import br.com.futura.agendafinanceira.services.SetorService;
import br.com.futura.agendafinanceira.utils.RelatorioUtil;
import net.sf.jasperreports.engine.JRException;

@Named
@ViewScoped
public class RelatorioPagamentosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<Setor> setores;
	private List<Conta> contas;
	private List<Fornecedor> fornecedores;
	
	private RelatorioFiltroDto filtro;
	
	@Inject
	private SetorService setorService;
	
	@Inject
	private ContaService contaService;
	
	@Inject
	private FornecedorService fornecedorService;
	
	@Inject
	private PagamentoParcelaService parcelaService;
	
	@PostConstruct
	private void init() {
		this.setores = setorService.listarTodos();
		this.contas = contaService.listarTodos();
		this.fornecedores = fornecedorService.listarTodos();
		
		this.filtro = new RelatorioFiltroDto();
		this.filtro.setDataInicial(new Date());
		this.filtro.setDataFinal(new Date());
	}
	
	public List<Setor> getSetores() {
		return setores;
	}
	
	public List<Conta> getContas() {
		return contas;
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public RelatorioFiltroDto getFiltro() {
		return filtro;
	}
	
	public void setFiltro(RelatorioFiltroDto filtro) {
		this.filtro = filtro;
	}
	
	public void gerarRelatorioContasAPagar() throws JRException, IOException, NenhumResultadoException {
	
		HashMap<String, String> parametros = new HashMap<>();
		List<PagamentoParcela> dados = parcelaService.listarPor(filtro);
		
		for (PagamentoParcela parcela : dados) {
			System.out.println("");
			System.out.println("-------------------------------");
			System.out.println("PARCELA >>>>>>>>>> " + parcela);
			
		}
		
		RelatorioUtil.imprimeRelatorio("ContasAPagar", parametros, dados);
	}
	
	public void gerarRelatorioPagamentosEfetuados() {
		
		System.out.println(">>>>>>>>>>>>> GERANDO RELATORIO Pagamentos Efetuados - Dados: " + filtro);
		
	}

}
