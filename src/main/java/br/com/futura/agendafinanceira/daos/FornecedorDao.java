package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.utils.NumberConversionUtil;

public class FornecedorDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	public List<Fornecedor> listarTodos() {
		return manager
				.createQuery("SELECT f FROM Fornecedor f order by f.nomeFantasia ", Fornecedor.class)
				.getResultList();
	}

	public List<Fornecedor> listarAtivos() {
		return manager
				.createQuery("SELECT f FROM Fornecedor f where f.ativo = :pAtivo order by f.nomeFantasia ", Fornecedor.class)
				.setParameter("pAtivo", Ativo.ATIVO)
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
	
	public List<Fornecedor> listarPor(String filtro) {
		return manager
				.createQuery("SELECT f FROM Fornecedor f "
						+ "WHERE f.idFornecedor=:pIdFornecedor "
						+ "OR f.cpfCnpj LIKE :pCpfCnpj "
						+ "OR f.nomeFantasia LIKE :pNomeFantasia "
						+ "OR f.razaoSocial LIKE :pRazaoSocial ", Fornecedor.class)
				.setParameter("pIdFornecedor", NumberConversionUtil.getIntegerOrZero(filtro))
				.setParameter("pCpfCnpj", "%" + filtro + "%")
				.setParameter("pNomeFantasia", "%" + filtro + "%")
				.setParameter("pRazaoSocial", "%" + filtro + "%")
				.getResultList();
		
	}
	
	public void salvar(Fornecedor fornecedor) {
		if (fornecedor.getIdFornecedor() != null) {
			manager.merge(fornecedor);
		} else {
			manager.persist(fornecedor);
		}
	}

	public void excluir(List<Fornecedor> fornecedores) {
		for (Fornecedor fornecedor : fornecedores) {
			manager.remove(manager.getReference(Fornecedor.class, fornecedor.getIdFornecedor()));
		}
	}
	

}
