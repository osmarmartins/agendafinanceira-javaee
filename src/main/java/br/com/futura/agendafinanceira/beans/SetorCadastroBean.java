package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.services.SetorService;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Model
public class SetorCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Setor setor;
	
	@Inject
	private SetorService setorService;
	
	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.setor = new Setor();
		this.setor.setAtivo(Ativo.ATIVO);
	}

	public String salvar() {
		setorService.salvar(setor);
		messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso!"));
		return "setor?faces-redirect=true";
	}

	public Setor getSetor() {
		if (setor == null){
			init();
		}
		return this.setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public void testar() {
		
	}
}
