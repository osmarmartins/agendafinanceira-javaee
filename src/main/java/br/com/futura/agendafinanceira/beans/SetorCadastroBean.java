package br.com.futura.agendafinanceira.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.SetorDao;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Model
public class SetorCadastroBean {

	private Setor setor;
	
	@Inject
	private SetorDao setorDao;
	
	@Inject
	private MessagesHelper messagesHelper;

	private boolean status;

	@PostConstruct
	private void init() {
		this.setor = new Setor();
		this.status = true;
		this.setor.setAtivo(Ativo.ATIVO);
	}

	public void salvar() {
		System.out.println("Salvar setor: " + this.setor.toString());
		setorDao.salvar(setor);
		messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso!"));
		init();
	}

	public Setor getSetor() {
		return this.setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.setor.setAtivo((status ? Ativo.ATIVO : Ativo.INATIVO));
		this.status = status;
	}
}
