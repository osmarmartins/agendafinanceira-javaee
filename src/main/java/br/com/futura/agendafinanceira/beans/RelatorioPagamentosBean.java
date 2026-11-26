package br.com.futura.agendafinanceira.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.futura.agendafinanceira.dto.RelatorioFiltroDto;
import br.com.futura.agendafinanceira.exceptions.NenhumResultadoException;
import br.com.futura.agendafinanceira.models.Conta;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.PagamentoParcela;
import br.com.futura.agendafinanceira.models.PagamentoQuitacao;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.services.BaixaService;
import br.com.futura.agendafinanceira.services.ContaService;
import br.com.futura.agendafinanceira.services.FornecedorService;
import br.com.futura.agendafinanceira.services.PagamentoParcelaService;
import br.com.futura.agendafinanceira.services.SetorService;
import br.com.futura.agendafinanceira.utils.DataUtil;
import br.com.futura.agendafinanceira.utils.MessagesHelper;
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
	
	@Inject
	private BaixaService baixaService;

	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.setores = setorService.listarTodos();
		this.contas = contaService.listarTodos();
		this.fornecedores = fornecedorService.listarTodos();

		limparFiltros();
	}

	public void limparFiltros() {
		this.filtro = new RelatorioFiltroDto();
		this.filtro.setDataInicial(DataUtil.primeiroDiaDoMes());
		this.filtro.setDataFinal(DataUtil.ultimoDiaDoMes());
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
	
	/*
	 * Chamados por ajax antes de abrir o PDF: sem registros, a mensagem aparece na própria tela
	 * (o PDF abre em outra aba). Com registros, clica no botão oculto que gera o relatório.
	 */
	public void validarRelatorioContasAPagar() {
		try {
			parcelaService.listarPor(filtro);
			RequestContext.getCurrentInstance().execute("document.getElementById('btnGerarPdf').click();");
		} catch (NenhumResultadoException e) {
			avisarNenhumRegistro();
		}
	}

	public void validarRelatorioPagamentosEfetuados() {
		try {
			baixaService.listarPor(filtro);
			RequestContext.getCurrentInstance().execute("document.getElementById('btnGerarPdf').click();");
		} catch (NenhumResultadoException e) {
			avisarNenhumRegistro();
		}
	}

	public void gerarRelatorioContasAPagar() throws JRException, IOException {

		HashMap<String, Object> parametros = new HashMap<>();
		List<PagamentoParcela> dados = new ArrayList<>();

		try {
			dados = parcelaService.listarPor(filtro);
		} catch (NenhumResultadoException e) {
			avisarNenhumRegistro();
			return;
		}
		parametros.put("periodo", filtro.periodo());
		parametros.put("filtros", filtro.selecao());
		RelatorioUtil.imprimeRelatorio("ContasAPagar", parametros, dados);


	}

	public void gerarRelatorioPagamentosEfetuados() throws JRException, IOException {

		HashMap<String, Object> parametros = new HashMap<>();
		List<PagamentoQuitacao> dados = new ArrayList<>();

		try {
			dados = baixaService.listarPor(filtro);
		} catch (NenhumResultadoException e) {
			avisarNenhumRegistro();
			return;
		}
		parametros.put("periodo", filtro.periodo());
		parametros.put("filtros", filtro.selecao());
		RelatorioUtil.imprimeRelatorio("PagamentosEfetuados", parametros, dados);

	}

	private void avisarNenhumRegistro() {
		messagesHelper.add(new FacesMessage(FacesMessage.SEVERITY_WARN,
				"Não foi encontrado registros para esse período e/ou filtros", null));
	}

}
