package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.futura.agendafinanceira.daos.SetorDao;
import br.com.futura.agendafinanceira.models.Setor;
import br.com.futura.agendafinanceira.utils.MessagesHelper;

@Named
@ViewScoped
public class SetorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Setor setor;

	private List<Setor> setores = new ArrayList<Setor>();

	private String pesquisaDescricao;

	@Inject
	private MessagesHelper messagesHelper;

	@Inject
	private SetorDao setorDao;

	@PostConstruct
	private void init() {
		this.setores = setorDao.listarTodos();
		this.pesquisaDescricao = new String();
	}

	public void pesquisar() {
		if (this.pesquisaDescricao != null && !this.pesquisaDescricao.isEmpty()) {
			this.setores = setorDao.listarPorDescricao(this.pesquisaDescricao);
		}
	}

	public String alterar(Setor setor) {
		return "/setorcadastro?faces-redirect=true&setor=" + setor.getIdSetor();
	}

	public void excluir(Setor setor) {
		setorDao.excluir(setor);
		init();
		messagesHelper.addFlash(new FacesMessage("Operação realizada com sucesso!"));
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

	public void setPesquisaDescricao(String pesquisaDescricao) {
		this.pesquisaDescricao = pesquisaDescricao;
	}

	public String getPesquisaDescricao() {
		return pesquisaDescricao;
	}

}
