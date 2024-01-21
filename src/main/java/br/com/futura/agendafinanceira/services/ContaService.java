package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.ContaDao;
import br.com.futura.agendafinanceira.models.Conta;

public class ContaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContaDao contaDao;

	public List<Conta> listarTodos() {
		return contaDao.listarTodos();
	}

	public List<Conta> listarAtivos() {
		return contaDao.listarAtivos();
	}

	public List<Conta> listarPorDescricao(String pesquisaDescricao) {
		return contaDao.listarPorDescricao(pesquisaDescricao);
	}

	@Transactional
	public void excluir(List<Conta> contas) {
		// TODO Validar exclusão de contas
		contaDao.excluir(contas);
		
	}

	@Transactional
	public void salvar(Conta conta) {
		contaDao.salvar(conta);
		
	}

}
