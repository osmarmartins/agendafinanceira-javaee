package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.futura.agendafinanceira.daos.ContaDao;
import br.com.futura.agendafinanceira.models.Conta;

public class ContaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContaDao contaDao;

	public List<Conta> listarTodos() {
		return contaDao.listarTodos();
	}

	public List<Conta> listarPorDescricao(String pesquisaDescricao) {
		return contaDao.listarPorDescricao(pesquisaDescricao);
	}

	public void excluir(Conta conta) {
		// TODO Validar exclusão de contas
		contaDao.excluir(conta);
		
	}

	public void salvar(Conta conta) {
		contaDao.salvar(conta);
		
	}
	
	

}
