package br.com.futura.agendafinanceira.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.SetorDao;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.models.enums.Ativo;

@Model
public class SetorCadastroBean {

	private Setor setor;
	
	@Inject
	private SetorDao setorDao;

	private boolean status;

	@PostConstruct
	private void init() {
		this.setor = new Setor();
		this.status = true;
		this.setor.setAtivo(Ativo.ATIVO);
	}

	public void salvar(ActionEvent event) {
		setorDao.salvar(setor);
		System.out.println("Salvar setor: " + this.setor.toString());
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
