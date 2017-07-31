package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.SetorDao;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Model
public class SetorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Setor setor;
	
	private List<Setor> setores = new ArrayList<Setor>();
	
	@Inject
	private MessagesHelper messagesHelper;

	@Inject
	private SetorDao setorDao;

	@PostConstruct
	private void init() {
		this.setores = setorDao.listarSetores();
	}

	public String alterar(Setor setor){
		return "/setorcadastro?faces-redirect=true&setor="+setor.getIdSetor();
	}
	
	public String excluir(Setor setor){
		setorDao.excluir(setor);
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
//		return "/setor?faces-redirect=true";
		return "/setorcadastro?faces-redirect=true&setor=1";
	}
	
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<Setor> getSetores() {
		return setores;
	}

}
