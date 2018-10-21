package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

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
	
	private Setor setorSelecionado = new Setor();

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

	public void excluir(Setor setor) {
		setorService.excluir(setor);
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

	public Setor getSetorSelecionado() {
		return setorSelecionado;
	}
	
	public void selecionaSetor(Setor setor) {
		this.setorSelecionado = setor;
	}
	
	public void setSetoresSelecionados(List<Setor> setoresSelecionados) {
		
		System.out.println(">>>>>>>>>>>>> (selecionou!) QTD REGISTROS SELECIONADOS: " + setoresSelecionados.size());
		
		this.setoresSelecionados = setoresSelecionados;
	}
	
	public List<Setor> getSetoresSelecionados() {
		return setoresSelecionados;
	}
	
	public Boolean isExisteSelecao() {
		
		System.out.println(">>>>>>>>>>>>> QTD REGISTROS SELECIONADOS: " + setoresSelecionados.size());
		
		return !setoresSelecionados.isEmpty();
	}
	
	public void ExibirSelecao() {
		
		System.out.println("REGISTROS SELECIONADOS:");
		
		for (Setor setor : setoresSelecionados) {
			System.out.println(setor);
		}
	}
	
    public void onRowSelect(SelectEvent event) {
		System.out.println(">>>>>>>>>>>>> QTD REGISTROS SELECIONADOS: " + setoresSelecionados.size());
    }
 
    public void onRowUnselect(UnselectEvent event) {
		System.out.println(">>>>>>>>>>>>> QTD REGISTROS SELECIONADOS: " + setoresSelecionados.size());
    }	

}
