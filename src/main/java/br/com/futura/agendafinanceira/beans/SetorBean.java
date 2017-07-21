package br.com.futura.agendafinanceira.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.SetorDao;
import br.com.futura.agendafinanceira.models.Setor;

@Model
public class SetorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Setor setor;

	@Inject
	private SetorDao setorDao;

	private List<Setor> setores = new ArrayList<Setor>();

	@PostConstruct
	private void init() {
		this.setores = setorDao.listarSetores();
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
