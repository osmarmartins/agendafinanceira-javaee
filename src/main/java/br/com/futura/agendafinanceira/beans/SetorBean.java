package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.services.SetorService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class SetorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pesquisaDescricao;

	@Inject
	private MessagesHelper messagesHelper;

	@Inject
	private SetorService setorService;
	
	private List<Setor> setores = new ArrayList<>();
	
	private List<Setor> setoresSelecionados = new ArrayList<>();
	
	@PostConstruct
	private void init() {
		this.setores = setorService.listarTodos();
	}

	public void pesquisar() {
		if (this.pesquisaDescricao != null && !this.pesquisaDescricao.isEmpty()) {
			this.setores = setorService.listarPorDescricao(this.pesquisaDescricao);
		} else {
			init();
		}
	}

	public void excluir() {
		setorService.excluir(setoresSelecionados);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
		init();
		
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setPesquisaDescricao(String pesquisaDescricao) {
		this.pesquisaDescricao = pesquisaDescricao;
	}

	public String getPesquisaDescricao() {
		return pesquisaDescricao;
	}

	public void selecionaSetor(Setor setor) {
		setoresSelecionados = new ArrayList<>();
		setoresSelecionados.add(setor);
	}
	
	public void setSetoresSelecionados(List<Setor> setoresSelecionados) {
		this.setoresSelecionados = setoresSelecionados;
	}
	
	public List<Setor> getSetoresSelecionados() {
		return setoresSelecionados;
	}
	
	public Boolean isExisteSelecao() {
		return !setoresSelecionados.isEmpty();
	}
	
	public String mensagemExclusao() {
		if (this.getSetoresSelecionados()==null || this.getSetoresSelecionados().size()==0) {
			return "";
		}
		
		StringBuilder msg = new StringBuilder();
		msg.append("Excluir permanentemente ");
		if (this.setoresSelecionados.size()>1) {
			msg.append("os setores selecionados?");
		}else {
			msg.append("o setor ");
			msg.append(setoresSelecionados.get(0).getDescricao());
		}
		return msg.toString();
	}

}
