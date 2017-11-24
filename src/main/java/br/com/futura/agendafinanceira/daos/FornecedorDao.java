package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.utils.NumberConversionUtil;

public class FornecedorDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	public List<Fornecedor> listarTodos() {
		return manager
				.createQuery("SELECT f FROM Fornecedor f ", Fornecedor.class)
				.getResultList();
	}

	public Fornecedor pesquisaPorId(Integer idFornecedor) {
		return manager
				.createQuery("SELECT f FROM Fornecedor f "
						+ "LEFT JOIN FETCH f.contatos "
						+ "WHERE f.idFornecedor=:pIdFornecedor", Fornecedor.class)
				.setParameter("pIdFornecedor", idFornecedor)
				.getSingleResult();
	}
	
	public List<Fornecedor> listarPor(String pesquisa) {
		return manager
				.createQuery("SELECT f FROM Fornecedor f "
						+ "WHERE f.idFornecedor=:pIdFornecedor "
						+ "OR f.cpfCnpj LIKE :pCpfCnpj "
						+ "OR f.nomeFantasia LIKE :pNomeFantasia "
						+ "OR f.razaoSocial LIKE :pRazaoSocial ", Fornecedor.class)
				.setParameter("pIdFornecedor", NumberConversionUtil.getIntegerOrZero(pesquisa))
				.setParameter("pCpfCnpj", "%" + pesquisa + "%")
				.setParameter("pNomeFantasia", "%" + pesquisa + "%")
				.setParameter("pRazaoSocial", "%" + pesquisa + "%")
				.getResultList();
		
	}
	
	@Transactional
	public void salvar(Fornecedor fornecedor) {
		if (fornecedor.getIdFornecedor() != null) {
			manager.merge(fornecedor);
		} else {
			manager.persist(fornecedor);
		}
	}

	@Transactional
	public void excluir(Fornecedor fornecedor) {
		manager.remove(manager.getReference(Fornecedor.class, fornecedor.getIdFornecedor()));
	}
	

}
