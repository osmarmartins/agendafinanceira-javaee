package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.exceptions.NegocioException;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.services.SetorService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class SetorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MessagesHelper messagesHelper;

	@Inject
	private SetorService setorService;
	
	private List<Setor> setores = new ArrayList<>();
	
	private List<Setor> setoresSelecionados = new ArrayList<>();
	
	private String mensagemExclusao;
	
	private String pesquisaFiltro;
	
	@PostConstruct
	private void init() {
		this.setores = setorService.listarTodos();
		this.setoresSelecionados = new ArrayList<>();
		this.mensagemExclusao = new String();
		this.pesquisaFiltro = new String();
	}
	
	public void pesquisar() {
		if (this.pesquisaFiltro != null && !this.pesquisaFiltro.isEmpty()) {
			this.setores = setorService.listarPorDescricao(this.pesquisaFiltro);
		} else {
			init();
		}
	}

	public void excluir() {
		try {
			setorService.excluir(setoresSelecionados);
		} catch (Exception e) {
			throw new NegocioException("Não foi possível realizar a exclusão! Verifique se há dependencias para esse(s) registro(s).", e);
		}
		
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
	}
	
	public void selecionaSetor(Setor setor) {
		setoresSelecionados.add(setor);
		mensagemExclusaoBuilder();
	}
	
	public Boolean isExisteSelecao() {
		return !setoresSelecionados.isEmpty();
	}
	
	public void mensagemExclusaoBuilder() {
		StringBuilder msg = new StringBuilder();

		if (this.getSetoresSelecionados()!=null && !this.getSetoresSelecionados().isEmpty()) {
			msg.append("Excluir permanentemente ");
			if (this.setoresSelecionados.size()>1) {
				msg.append("os setores selecionados?");
			}else {
				msg.append("o setor ");
				msg.append(setoresSelecionados.get(0).getDescricao());
			}
		}
		this.mensagemExclusao = msg.toString();
	}
	
	public List<Setor> getSetores() {
		return setores;
	}

	public String getPesquisaFiltro() {
		return pesquisaFiltro;
	}
	
	public void setPesquisaFiltro(String pesquisaFiltro) {
		this.pesquisaFiltro = pesquisaFiltro;
	}
	
	public void setSetoresSelecionados(List<Setor> setoresSelecionados) {
		this.setoresSelecionados = setoresSelecionados;
	}
	
	public List<Setor> getSetoresSelecionados() {
		return setoresSelecionados;
	}
	
	public String getMensagemExclusao() {
		return mensagemExclusao;
	}
		
}
