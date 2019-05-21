package br.com.futura.agendafinanceira.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.daos.FornecedorDao;
import br.com.futura.agendafinanceira.models.Fornecedor;

public class FornecedorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FornecedorDao fornecedorDao;

	public List<Fornecedor> listarPor(String filtro) {
		return fornecedorDao.listarPor(filtro);
	}

	public List<Fornecedor> listarTodos() {
		return fornecedorDao.listarTodos();
	}

	public List<Fornecedor> listarAtivos() {
		return fornecedorDao.listarAtivos();
	}

	@Transactional
	public void excluir(List<Fornecedor> fornecedores) {
		// TODO Validar exclusão de fornecedores 
		fornecedorDao.excluir(fornecedores);
	}
	
	@Transactional
	public void salvar(Fornecedor fornecedor) {
		fornecedorDao.salvar(fornecedor);
	}
	

}
