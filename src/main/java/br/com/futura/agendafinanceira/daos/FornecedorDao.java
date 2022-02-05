package br.com.futura.agendafinanceira.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.futura.agendafinanceira.dto.FornecedorFiltroDto;
import br.com.futura.agendafinanceira.models.Fornecedor;
import br.com.futura.agendafinanceira.models.enums.Ativo;
import br.com.futura.agendafinanceira.utils.NumberConversionUtil;

public class FornecedorDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	public List<Fornecedor> listarTodos() {
		return em
				.createQuery("SELECT f FROM Fornecedor f order by f.nomeFantasia ", Fornecedor.class)
				.getResultList();
	}

	public List<Fornecedor> listarAtivos() {
		return em
				.createQuery("SELECT f FROM Fornecedor f join fetch f.conta where f.ativo = :pAtivo order by f.nomeFantasia ", Fornecedor.class)
				.setParameter("pAtivo", Ativo.ATIVO)
				.getResultList();
	}

	public Fornecedor pesquisaPorId(Integer idFornecedor) {
		return em
				.createQuery("SELECT f FROM Fornecedor f "
						+ "LEFT JOIN FETCH f.contatos "
						+ "WHERE f.idFornecedor=:pIdFornecedor", Fornecedor.class)
				.setParameter("pIdFornecedor", idFornecedor)
				.getSingleResult();
	}
	
	public void salvar(Fornecedor fornecedor) {
		if (fornecedor.getIdFornecedor() != null) {
			em.merge(fornecedor);
		} else {
			em.persist(fornecedor);
		}
	}

	public void excluir(List<Fornecedor> fornecedores) {
		for (Fornecedor fornecedor : fornecedores) {
			em.remove(em.getReference(Fornecedor.class, fornecedor.getIdFornecedor()));
		}
	}

	@SuppressWarnings("unchecked")
	public Integer contarRegistros(FornecedorFiltroDto filtro) {
		StringBuilder sql = new StringBuilder("select count(r) from Fornecedor r ");
		TypedQuery<Long> query = (TypedQuery<Long>) aplicarFiltro(filtro, sql, Long.class);
		return query.getSingleResult().intValue();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> listarPor(FornecedorFiltroDto filtro) {
		StringBuilder sql = new StringBuilder("SELECT r FROM Fornecedor r ");
		
		TypedQuery<Fornecedor> query = (TypedQuery<Fornecedor>) aplicarFiltro(filtro, sql, Fornecedor.class);

		query.setFirstResult(filtro.getPaginacao().getPagina());
		query.setMaxResults(filtro.getPaginacao().getRegistrosPorPagina());
		
		return query.getResultList();		
	}	
	
	private TypedQuery<?> aplicarFiltro(FornecedorFiltroDto filtro, StringBuilder sql, Class<?> classe) {
		if (filtro.getDescricao() != null) {
			sql.append("where r.idFornecedor=:pIdFornecedor ");
			sql.append("OR r.cpfCnpj LIKE :pCpfCnpj ");
			sql.append("OR r.nomeFantasia LIKE :pNomeFantasia ");
			sql.append("OR r.razaoSocial LIKE :pRazaoSocial ");
		}
				
		if (filtro.getPaginacao().getOrdenacao() != null) {
			sql.append(" order by r." + filtro.getPaginacao().getOrdenacao());
			if (!filtro.getPaginacao().isAscendente()) {
				sql.append(" desc "); 
			}
		}else {
			sql.append(" order by r.nomeFantasia ");
		}
		
		
		TypedQuery<?> query = em.createQuery(sql.toString(), classe);
		
		if (filtro.getDescricao() != null) {
			query
				.setParameter("pIdFornecedor", NumberConversionUtil.getIntegerOrZero(filtro.getDescricao()))
				.setParameter("pCpfCnpj", "%" + filtro.getDescricao() + "%")
				.setParameter("pNomeFantasia", "%" + filtro.getDescricao() + "%")
				.setParameter("pRazaoSocial", "%" + filtro.getDescricao() + "%");
		}
		
		return query;
	}	
	

}
