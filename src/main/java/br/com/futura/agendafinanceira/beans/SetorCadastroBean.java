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

	@PostConstruct
	private void init() {
		this.setor = new Setor();
		this.setor.setAtivo(Ativo.ATIVO);
	}

	public void salvar() {
		setorDao.salvar(setor);
		messagesHelper.addFlash(new FacesMessage("Operação concluida com sucesso!"));
		init();
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
