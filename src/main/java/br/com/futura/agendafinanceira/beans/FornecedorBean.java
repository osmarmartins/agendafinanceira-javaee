package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.futura.agendafinanceira.dto.FornecedorFiltroDto;
import br.com.futura.agendafinanceira.dto.PaginacaoDto;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.services.FornecedorService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private LazyDataModel<Fornecedor> fornecedores;
	
	@Inject
	private FornecedorService fornecedorService;
	
	@Inject
	private MessagesHelper messagesHelper;

	private List<Fornecedor> fornecedoresSelecionados;
	
	private String mensagemExclusao;
	
	private FornecedorFiltroDto filtro = new FornecedorFiltroDto();
	
	private Integer qtdRegistros;
	
	@PostConstruct
	private void init(){
		this.fornecedoresSelecionados = new ArrayList<>();
		this.mensagemExclusao = new String();
		pesquisar();
	}
	
	public void pesquisar(){
		this.qtdRegistros = fornecedorService.contarRegistros(filtro);
		this.fornecedores = new LazyDataModel<Fornecedor>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<Fornecedor> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				
				PaginacaoDto paginacao = filtro.getPaginacao();
							
				paginacao.setPagina(first);
				paginacao.setRegistrosPorPagina(pageSize);
				paginacao.setOrdenacao(sortField);
				paginacao.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				
				setRowCount(qtdRegistros);
				
				return fornecedorService.listarPor(filtro);
			}	
			
			@Override
			public Object getRowKey(Fornecedor fornecedor) {
				return fornecedor.getIdFornecedor();
			}
			
			@Override
			public Fornecedor getRowData(String rowKey) {
				for (Fornecedor fornecedor : fornecedores) {
					if (Integer.toString(fornecedor.getIdFornecedor()).equals(rowKey)) {
						return fornecedor;
					}
				}
				return null;
			}
		};
	}		
		

	public void excluir(){
		fornecedorService.excluir(fornecedoresSelecionados);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public void selecionaFornecedor(Fornecedor fornecedor) {
		fornecedoresSelecionados.add(fornecedor);
		mensagemExclusaoBuilder();
	}
	
	public Boolean isExisteSelecao() {
		return !fornecedoresSelecionados.isEmpty();
	}
	
	public void mensagemExclusaoBuilder() {
		StringBuilder msg = new StringBuilder();

		if (this.getFornecedoresSelecionados()!=null && !this.getFornecedoresSelecionados().isEmpty()) {
			msg.append("Excluir permanentemente ");
			if (this.fornecedoresSelecionados.size()>1) {
				msg.append("os fornecedores selecionados?");
			}else {
				msg.append("o fornecedor ");
				msg.append(fornecedoresSelecionados.get(0).getRazaoSocial());
			}
		}
		this.mensagemExclusao = msg.toString();
	}
	
	public List<Fornecedor> getFornecedoresSelecionados() {
		return fornecedoresSelecionados;
	}
	
	public void setFornecedoresSelecionados(List<Fornecedor> fornecedoresSelecionados) {
		this.fornecedoresSelecionados = fornecedoresSelecionados;
	}
	
	public String getMensagemExclusao() {
		return mensagemExclusao;
	}
	
	public void setMensagemExclusao(String mensagemExclusao) {
		this.mensagemExclusao = mensagemExclusao;
	}
	
	public FornecedorFiltroDto getFiltro() {
		return filtro;
	}
	
	public void setFiltro(FornecedorFiltroDto filtro) {
		this.filtro = filtro;
	}
	
	public LazyDataModel<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void setFornecedores(LazyDataModel<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
}
