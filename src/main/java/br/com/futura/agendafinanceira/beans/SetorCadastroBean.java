package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.SetorDao;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class SetorCadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Setor setor;
	
	@Inject
	private SetorDao setorDao;
	
	@Inject
	private MessagesHelper messagesHelper;

	@PostConstruct
	private void init() {
		this.setor = new Setor();
		this.setor.setAtivo(Ativo.ATIVO);
	}

	public String salvar() {
		setorDao.salvar(setor);
		messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso!"));
		init();
		return "setorcadastro?faces-redirect=true";
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
}
